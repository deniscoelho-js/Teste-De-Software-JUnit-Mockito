package br.com.denis.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
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
	public void testLocacao() throws Exception {
//		cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("filme 1", 1, 5.0)) ;

//		ação
		Locacao locacao = service.alugarFilme(usuario, filmes);

//		verificacao
		assertEquals(5.0, locacao.getValor(), 0.01);
		assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
	}

	@Test(expected = FilmesSemEstoqueException.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
//		cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("filme 1", 0, 5.0)) ;

//		ação
		service.alugarFilme(usuario, filmes);

	}

//	@Test
//	public void testLocacao_usuarioVazio() throws FilmesSemEstoqueException {
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

}
