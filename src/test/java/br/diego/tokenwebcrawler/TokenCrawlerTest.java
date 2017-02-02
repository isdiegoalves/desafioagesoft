package br.diego.tokenwebcrawler;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.script.ScriptException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import tokenwebcrawler.PaginaCalculo;
import tokenwebcrawler.PaginaDesafioAgesoft;

public class TokenCrawlerTest {

	@Test
	public void dadoUmaOperacaoMatematicaQuandoResolver1000OperacoesEntaoExibirOValorToken() throws FailingHttpStatusCodeException, MalformedURLException, IOException, NumberFormatException, ScriptException {

		PaginaDesafioAgesoft desafio = new PaginaDesafioAgesoft();
		
		int numeroPagina = 100;
		PaginaCalculo paginaCalculo = desafio.comecar().resolverQuestoesAteAPagina(numeroPagina);
		
		assertEquals(numeroPagina, paginaCalculo.getNumero());
		
		String token = paginaCalculo.getToken();

		System.out.printf("token = %s pagina nº = %s", token, paginaCalculo.getNumero());
	}

}
