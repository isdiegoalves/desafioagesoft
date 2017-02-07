package br.diego.tokenwebcrawler;

import static br.diego.tokenwebcrawler.Pagina.html;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class PaginaDesafioTest {
	
	private HtmlPage agesoft;
	private HtmlPage google;

	@Before
	public void setUp() {
		agesoft = html("https://agsoft.herokuapp.com");
		google = html("https://www.google.com.br/");
	}
	
	@Test
	public void dadoUmHtmlPageNaoNuloQuandoForAPaginaDesafioEntaoLancarIllegalStateException() {
		new PaginaDesafio(agesoft);
	}

	@Test(expected=NullPointerException.class)
	public void dadoUmHtmlPageNuloQuandoInvocarConstrutorEntaoLancarNullPointerException() {
		new PaginaDesafio(null);
	}
	
	@Test(expected=IllegalStateException.class)
	public void dadoUmHtmlPageNaoNuloQuandoNaoForAPaginaDesafioEntaoLancarIllegalStateException() {
		new PaginaDesafio(google);
	}
	
	@Test
	public void dadoPaginaDesafioCorretaQuandoClicarEmComecarEntaoRetornarPaginaCalculo() {
		PaginaDesafio desafio = new PaginaDesafio(agesoft);
		PaginaCalculo paginaCalculo = desafio.comecar();
		assertThat(paginaCalculo.getNumero(), equalTo(1));
		assertThat(paginaCalculo.getNumero(), equalTo(1));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void dadoPaginaDesafioUrlCorretaQuandoClicarEmComecarEntaoLancarUnsupportedOperationException() throws IOException {
		agesoft = mock(HtmlPage.class);
		HtmlSubmitInput botaoComecar = mock(HtmlSubmitInput.class);
		
		when(agesoft.asText()).thenReturn("Um token composto por 64 caracteres vai ser exibido cada vez que uma nova página for carregada");
		when(agesoft.getFirstByXPath("//input[@type='submit']")).thenReturn(botaoComecar);
		
		doThrow(IOException.class).when(botaoComecar).click();

		PaginaDesafio desafio = new PaginaDesafio(agesoft);
		desafio.comecar();
	}
}
