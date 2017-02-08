package br.diego.tokenwebcrawler;

import static br.diego.tokenwebcrawler.Pagina.html;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Method;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class PaginaCalculoTest {
	
	private PaginaCalculo paginaCalculo;

	@Before
	public void setUp() {
		paginaCalculo = new PaginaDesafio(html("https://agsoft.herokuapp.com")).comecar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void dadoUmNumeroNegativoQuandoResolverQuestoesEntaoLancarNullPointerException() {
		
		paginaCalculo.resolverQuestoesAteAPagina(-1);
	}
	
	@Test
	public void dadoUmNumeroPositivoQuandoResolverQuestoesEntaoRetornarResultado() {
		
		PaginaCalculo resultado = paginaCalculo.resolverQuestoesAteAPagina(1);
		
		assertThat(resultado.getNumero(), equalTo(1));
		assertThat(resultado.getToken().length(), equalTo(64));
	}
	
	@Test(expected=NullPointerException.class)
	public void dadoUmaRespostaNulaQuandoEnviarRespostaEntaoLancarNullPointerException() {
		
		try {
			Method method = paginaCalculo.getClass().getDeclaredMethod("enviar", Number.class);
			method.setAccessible(true);
			method.invoke(paginaCalculo, (Number)null);
		} catch (Exception e) {
			if(NullPointerException.class.isAssignableFrom(e.getCause().getClass()))
				throw new NullPointerException(e.getMessage());
		}
		fail();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void dadoUmaRespostaNaoNulaQuandoEnviarRespostaEntaoLancarNullPointerException() throws IOException {
		HtmlPage htmlCalculo = mock(HtmlPage.class);
		HtmlSubmitInput btnEnviarResposta = mock(HtmlSubmitInput.class);
		HtmlTextInput htmlInputEnviar = mock(HtmlTextInput.class);
		when(htmlCalculo.getFirstByXPath("//input[@type='submit']")).thenReturn(btnEnviarResposta);
		when(htmlCalculo.<HtmlTextInput>getElementByName("captcha")).thenReturn(htmlInputEnviar);
		doThrow(IOException.class).when(btnEnviarResposta).click();

		HtmlPage htmlDesafio = mock(HtmlPage.class);
		HtmlSubmitInput botaoComecar = mock(HtmlSubmitInput.class);
		when(htmlDesafio.asText()).thenReturn("Um token composto por 64 caracteres vai ser exibido cada vez que uma nova página for carregada");
		when(htmlDesafio.getFirstByXPath("//input[@type='submit']")).thenReturn(botaoComecar);
		when(botaoComecar.click()).thenReturn(htmlCalculo);

		paginaCalculo = new PaginaDesafio(htmlDesafio).comecar();
		
		try {
			Method method = paginaCalculo.getClass().getDeclaredMethod("enviar", Number.class);
			method.setAccessible(true);
			method.invoke(paginaCalculo, (Number)4);
		} catch (Exception e) {
			if(UnsupportedOperationException.class.isAssignableFrom(e.getCause().getClass()))
				throw new UnsupportedOperationException(e.getMessage());
		}
		fail();
	}
	
	public void dadoUmaRespostaNaoNulaQuandoEnviarRespostaEntaoRetornarHtmlPageProximaPagina() throws IOException {
		HtmlPage htmlCalculo = mock(HtmlPage.class);
		HtmlSubmitInput btnEnviarResposta = mock(HtmlSubmitInput.class);
		HtmlTextInput htmlInputEnviar = mock(HtmlTextInput.class);
		when(htmlCalculo.getFirstByXPath("//input[@type='submit']")).thenReturn(btnEnviarResposta);
		when(htmlCalculo.<HtmlTextInput>getElementByName("captcha")).thenReturn(htmlInputEnviar);
		when(btnEnviarResposta.click()).thenReturn(htmlCalculo);

		HtmlPage htmlDesafio = mock(HtmlPage.class);
		HtmlSubmitInput botaoComecar = mock(HtmlSubmitInput.class);
		when(htmlDesafio.asText()).thenReturn("Um token composto por 64 caracteres vai ser exibido cada vez que uma nova página for carregada");
		when(htmlDesafio.getFirstByXPath("//input[@type='submit']")).thenReturn(botaoComecar);
		when(botaoComecar.click()).thenReturn(htmlCalculo);

		paginaCalculo = new PaginaDesafio(htmlDesafio).comecar();
		
		try {
			Method method = paginaCalculo.getClass().getDeclaredMethod("enviar", Number.class);
			method.setAccessible(true);
			HtmlPage proximaHtmlPage = (HtmlPage) method.invoke(paginaCalculo, (Number)4);
			MatcherAssert.assertThat(proximaHtmlPage, notNullValue());
		} catch (Exception e) {
			fail();
		}
	}
}
