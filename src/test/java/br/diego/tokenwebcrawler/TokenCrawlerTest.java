package br.diego.tokenwebcrawler;

import static br.diego.tokenwebcrawler.Pagina.html;
import static java.lang.Integer.parseInt;
import static java.lang.System.getProperty;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class TokenCrawlerTest {

	private PaginaCalculo paginaCalculo;

	@Before
	public void setUp() {
		paginaCalculo = new PaginaDesafio(html("https://agsoft.herokuapp.com")).comecar();
	}

	@Test
	public void dadoUmaOperacaoMatematicaQuandoResolver1000OperacoesEntaoExtrairValorToken() {

		int paginaDesejada = parseInt(getProperty("pagina", "10"));

		paginaCalculo.resolverQuestoesAteAPagina(paginaDesejada);
		
		assertThat(paginaCalculo.getNumero(), equalTo(paginaDesejada));
		assertThat(paginaCalculo.getToken().length(), equalTo(64));
	}

}
