package br.com.denis.servicos;

import static br.com.denis.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.com.denis.entidades.Filme;
import br.com.denis.entidades.Locacao;
import br.com.denis.entidades.Usuario;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

	public static void main(String[] args) {
//		cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("usuario 1");
		Filme filme = new Filme("filme 1", 2, 5.5);
		
//		ação
		Locacao locacao =  service.alugarFilme(usuario, filme);
		
//		verificacao
		System.out.println(locacao.getValor());
		System.out.println(locacao.getDataLocacao());
		System.out.println(locacao.getDataRetorno());
	}
}