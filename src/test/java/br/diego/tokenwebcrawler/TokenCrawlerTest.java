package br.diego.tokenwebcrawler;

import static java.lang.Integer.parseInt;
import static java.lang.System.getProperty;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class TokenCrawlerTest {

	@Test
	public void dadoUmaOperacaoMatematicaQuandoResolver1000OperacoesEntaoExtrairValorToken() {

		int paginaDesejada = parseInt(getProperty("pagina", "10"));

		PaginaDesafioAgesoft desafio = new PaginaDesafioAgesoft();
		PaginaCalculo paginaCalculo = desafio.comecar().resolverQuestoesAteAPagina(paginaDesejada);
		
		assertThat(paginaCalculo.getNumero(), equalTo(paginaDesejada));
		assertThat(paginaCalculo.getToken().length(), equalTo(64));
	}

}
