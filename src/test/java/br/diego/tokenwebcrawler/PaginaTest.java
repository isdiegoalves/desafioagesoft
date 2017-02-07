package br.diego.tokenwebcrawler;

import org.junit.Test;

public class PaginaTest {

	@Test
	public void dadoUmaURLValidaQuandoInvocarHtmlEntaoCriarHtmlPage() {
		Pagina.html("https://agsoft.herokuapp.com");
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void dadoUmaUrlInvalidaQuandoInvocarConstrutorEntaoLancarIllegalArgumentException() {
		Pagina.html("url.invalida.com");
	}
	
	@Test(expected=NullPointerException.class)
	public void dadoUmaUrlNulaQuandoInvocarConstrutorEntaoLancarNullPointerException() {
		Pagina.html(null);
	}
}
