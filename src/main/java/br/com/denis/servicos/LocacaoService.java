package br.com.denis.servicos;

import static br.com.denis.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.com.denis.entidades.Filme;
import br.com.denis.entidades.Locacao;
import br.com.denis.entidades.Usuario;
import br.com.denis.exceptions.FilmesSemEstoqueException;
import br.com.denis.exceptions.LocadoraException;


public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmesSemEstoqueException, LocadoraException {
		
		if(filme.getEstoque() == 0) {
			throw new FilmesSemEstoqueException();
		}
		
		if(usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		
		if(filme == null) {
			throw new LocadoraException("Filme vazio");
		}
		
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar método para salvar

		return locacao;
	}

}