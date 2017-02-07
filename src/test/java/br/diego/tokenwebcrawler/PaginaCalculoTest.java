package br.diego.tokenwebcrawler;

import static br.diego.tokenwebcrawler.Pagina.html;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class PaginaCalculoTest {
	
	private PaginaCalculo problema;

	@Before
	public void setUp() {
		problema = new PaginaDesafio(html("https://agsoft.herokuapp.com")).comecar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void dadoUmNumeroNegativoQuandoResolverQuestoesEntaoLancarNullPointerException() {
		
		problema.resolverQuestoesAteAPagina(-1);
	}
	
	@Test
	public void dadoUmNumeroPositivoQuandoResolverQuestoesEntaoRetornarResultado() {
		
		PaginaCalculo resultado = problema.resolverQuestoesAteAPagina(1);
		
		assertThat(resultado.getNumero(), equalTo(1));
		assertThat(resultado.getToken().length(), equalTo(64));
	}
	
	@Test(expected=NullPointerException.class)
	public void dadoUmaRespostaNulaQuandoEnviarRespostaEntaoLancarNullPointerException() {
		try {
			Method method = problema.getClass().getDeclaredMethod("enviar", Number.class);
			method.setAccessible(true);
			method.invoke(problema, (Number)null);
		} catch (Exception e) {
			if(NullPointerException.class.isAssignableFrom(e.getCause().getClass()))
				throw new NullPointerException(e.getMessage());
		}
		fail();
	}
}
