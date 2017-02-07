package br.diego.tokenwebcrawler;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public final class PaginaDesafio {
	
	private HtmlPage page;
	
	public PaginaDesafio(HtmlPage page) throws NullPointerException, IllegalStateException {
		
		if (page == null) {
			throw new NullPointerException("HtmlPage não pode ser nulo!");
		}
		
		if (!page.asText().contains(
				"Um token composto por 64 caracteres vai ser exibido cada vez que uma nova página for carregada")) {
		
			throw new IllegalStateException("URL informada não é a do desafio!");
		}
		
		this.page = page;
	}
	
	public final PaginaCalculo comecar() throws UnsupportedOperationException {
		try {
			HtmlSubmitInput botaoComecar = this.page.getFirstByXPath("//input[@type='submit']");
			return new PaginaCalculo(botaoComecar.click());
		} catch (IOException e) {
			throw new UnsupportedOperationException(e);
		}
	}
}