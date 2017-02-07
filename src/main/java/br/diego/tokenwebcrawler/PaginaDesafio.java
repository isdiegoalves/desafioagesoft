package br.diego.tokenwebcrawler;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public final class PaginaDesafio {

	private HtmlPage page;

	public PaginaDesafio(HtmlPage page) {
		this.page = page;
	}

	public final PaginaCalculo comecar() throws UnsupportedOperationException {
		try {
			HtmlSubmitInput botaoComecar = page.getFirstByXPath("//input[@type='submit']");
			return new PaginaCalculo(botaoComecar.click());
		} catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}
}