package br.diego.tokenwebcrawler;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class PaginaDesafioAgesoft {

	public PaginaCalculo comecar() {
		try {
			HtmlPage index = new WebClient().getPage("https://agsoft.herokuapp.com");
			HtmlSubmitInput botaoComecar = index.getFirstByXPath("//input[@type='submit']");
			return new PaginaCalculo(botaoComecar.click());
		} catch(IOException e) {
			throw new UnsupportedOperationException(e);
		}
	}
}
