package br.diego.tokenwebcrawler;

import static br.diego.tokenwebcrawler.Matematico.resolverExpressaoMatematica;
import static java.lang.String.valueOf;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlBold;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class PaginaCalculo {

	private HtmlPage page;

	public PaginaCalculo(HtmlPage page) {
		this.page = page;
	}
	
	public final PaginaCalculo comecar() throws FailingHttpStatusCodeException {
		try {
			HtmlSubmitInput botaoComecar = page.getFirstByXPath("//input[@type='submit']");
			return new PaginaCalculo(botaoComecar.click());
		} catch (IOException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public PaginaCalculo resolverQuestoesAteAPagina(int numero) {
		
		if (numero < 1)
			throw new IllegalArgumentException("Não é permitido número de página menor que um!");
		
		while(getNumero() != numero) {
			
			final StringBuilder expressao = new StringBuilder();

			page.getByXPath("//span").stream()
			.map(HtmlSpan.class::cast)
			.map(HtmlSpan::getFirstChild)
			.forEach(expressao::append);

			Number resposta = resolverExpressaoMatematica(valueOf(expressao));
			
			page = enviar(resposta);
		}
		
		return this;
	}

	private HtmlPage enviar(Number resposta) {
		
		if (resposta == null)
			throw new NullPointerException("resposta não pode ser null!");
		
		try {
			page.<HtmlTextInput>getElementByName("captcha").setValueAttribute(valueOf(resposta));
			HtmlSubmitInput btnSubmit = page.getFirstByXPath("//input[@type='submit']");
			return btnSubmit.click();
		} catch (IOException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public String getToken() {
		return page.<HtmlBold>getFirstByXPath("//b").asText();
	}

	public int getNumero() {
		return Integer.parseInt(page.<HtmlHeading1>getFirstByXPath("//h1")
				.asText()
				.replaceAll("[^0-9]", ""));
	}
}
