package br.com.denis.servicos;

import static br.com.denis.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import br.com.denis.entidades.Filme;
import br.com.denis.entidades.Locacao;
import br.com.denis.entidades.Usuario;
import br.com.denis.exceptions.FilmesSemEstoqueException;
import br.com.denis.exceptions.LocadoraException;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes)
			throws FilmesSemEstoqueException, LocadoraException {

		if (usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}

		if (filmes == null || filmes.isEmpty()) {
			throw new LocadoraException("Filme vazio");
		}
		for(Filme filme : filmes) {
			if (filme.getEstoque() == 0) {
				throw new FilmesSemEstoqueException();
			}
		}
	

	Locacao locacao = new Locacao();
	locacao.setFilmes(filmes);
	locacao.setUsuario(usuario);
	locacao.setDataLocacao(new Date());
	Double valorTotal = 0d;
	
	for(Filme filme : filmes) {
		valorTotal += filme.getPrecoLocacao();
	}
	
	locacao.setValor(valorTotal);

	// Entrega no dia seguinte
	Date dataEntrega = new Date();
	dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar método para salvar

		return locacao;
	}

}