package br.com.kanjarana.kanjafood.domain.repository;

import java.util.List;

import br.com.kanjarana.kanjafood.domain.model.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> todos();
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Long id);
}
