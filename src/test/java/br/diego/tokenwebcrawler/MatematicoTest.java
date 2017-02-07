package br.diego.tokenwebcrawler;

import static org.hamcrest.CoreMatchers.equalTo;

import java.lang.reflect.Constructor;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class MatematicoTest {

	@Test
	public void dadoUmaExpressaoMatematicaValidaEntaoCalcular() {
		
		String expressao = "1+1";
		
		Number resultado = Matematico.resolverExpressaoMatematica(expressao);
		
		MatcherAssert.assertThat(resultado, equalTo(2));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void dadoUmaExpressaoMatematicaInvalidaEntaoLancarUnsupportedOperationException() {
		
		String expressao = "1+1-";
		
		Number resultado = Matematico.resolverExpressaoMatematica(expressao);
		
		MatcherAssert.assertThat(resultado, equalTo(2));
	}
	
	@Test
	public void dadoUmConstrutorPublicoEntaoInstanciar() throws Exception {
		Constructor<Matematico> constructor = Matematico.class.getDeclaredConstructor();
        constructor.newInstance();
	}
}
