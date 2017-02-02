package br.diego.tokenwebcrawler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.script.ScriptException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class TokenCrawlerTest {

	@Test
	public void dadoUmaOperacaoMatematicaQuandoResolver1000OperacoesEntaoExtrairValorToken() throws FailingHttpStatusCodeException, MalformedURLException, IOException, NumberFormatException, ScriptException {

		int paginaDesejada = 100;

		PaginaDesafioAgesoft desafio = new PaginaDesafioAgesoft();
		PaginaCalculo paginaCalculo = desafio.comecar().resolverQuestoesAteAPagina(paginaDesejada);
		
		assertThat(paginaCalculo.getNumero(), equalTo(paginaDesejada));
		assertThat(paginaCalculo.getToken().length(), equalTo(64));
	}

}
