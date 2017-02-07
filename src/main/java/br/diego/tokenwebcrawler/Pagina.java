package br.diego.tokenwebcrawler;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Pagina {

	public static HtmlPage agesoft() throws UnsupportedOperationException {
		try {
			return new WebClient().getPage("https://agsoft.herokuapp.com");
		} catch (FailingHttpStatusCodeException | IOException e) {
			throw new UnsupportedOperationException(e);
		}
	}
}
