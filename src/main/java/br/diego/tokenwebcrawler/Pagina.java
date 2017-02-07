package br.diego.tokenwebcrawler;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Pagina {
	
	public static HtmlPage html(String url) throws IllegalArgumentException {
		try {
			return  new WebClient().getPage(url);
		} catch (IOException | FailingHttpStatusCodeException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
