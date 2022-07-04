package br.com.denis.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;

import org.hamcrest.MatcherAssert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.com.denis.entidades.Filme;
import br.com.denis.entidades.Locacao;
import br.com.denis.entidades.Usuario;
import br.com.denis.exceptions.FilmesSemEstoqueException;
import br.com.denis.exceptions.LocadoraException;
import br.com.denis.utils.DataUtils;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Test
	public void deveAlugarFilme() throws Exception {
//		cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("filme 1", 1, 5.0));

//		ação
		Locacao locacao = service.alugarFilme(usuario, filmes);

//		verificacao
		assertEquals(5.0, locacao.getValor(), 0.01);
		assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
	}

	@Test(expected = FilmesSemEstoqueException.class)
	public void deveLancarExcecaoAoLancarFilmeSemEstoque() throws Exception {
//		cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("filme 1", 0, 5.0));

//		ação
		service.alugarFilme(usuario, filmes);

	}

//	@Test
//	public void naoDeveAlugarFilmeSemUsuario() throws FilmesSemEstoqueException {
////		cenario
//		LocacaoService service = new LocacaoService();
//		List<Filme> filmes = Arrays.asList(new Filme("filme 1", 1, 5.0)) ;
//
//		
////		acao
//		
//			try {
//				service.alugarFilme(null, filmes);
//				Assert.fail();
//			}  catch (LocadoraException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//
//	}

	@Test
	public void devePagar75pctFilme3() throws FilmesSemEstoqueException, LocadoraException {
		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
				new Filme("Filme 3", 4, 4.0));

		// acao
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// verificacao
//		assertEquals(resultado.getValor(), is(11.0));
		MatcherAssert.assertThat(resultado.getValor(), CoreMatchers.is(11.0));
	}

	@Test
	public void devePagar50pctFilme4() throws FilmesSemEstoqueException, LocadoraException {
		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
				new Filme("Filme 3", 4, 4.0), new Filme("Filme 4", 4, 4.0));

		// acao
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// verificacao
//		assertEquals(resultado.getValor(), is(11.0));
		MatcherAssert.assertThat(resultado.getValor(), CoreMatchers.is(13.0));
	}

	@Test
	public void devePagar25pctFilme5() throws FilmesSemEstoqueException, LocadoraException {
		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
				new Filme("Filme 3", 4, 4.0), new Filme("Filme 4", 4, 4.0), new Filme("Filme 5", 4, 4.0));

		// acao
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// verificacao
//		assertEquals(resultado.getValor(), is(11.0));
		MatcherAssert.assertThat(resultado.getValor(), CoreMatchers.is(14.0));
	}

	@Test
	public void devePagar0pctFilme6() throws FilmesSemEstoqueException, LocadoraException {
		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
				new Filme("Filme 3", 4, 4.0), new Filme("Filme 4", 4, 4.0), new Filme("Filme 5", 4, 4.0),
				new Filme("Filme 6", 4, 4.0));

		// acao
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// verificacao
//		assertEquals(resultado.getValor(), is(11.0));
		MatcherAssert.assertThat(resultado.getValor(), CoreMatchers.is(14.0));
	}
	
	@Test
	@Ignore
	public void naoDeveDevolverFilmeNoDomingo() throws FilmesSemEstoqueException, LocadoraException {
//		cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
//		acao
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
//		verificacao
		boolean isSegunda =  DataUtils.verificarDiaSemana(resultado.getDataRetorno(), Calendar.MONDAY);
		assertTrue(isSegunda);	

	}

}
