package br.com.kanjarana.kanjafood.domain.repository;

import java.util.List;

import br.com.kanjarana.kanjafood.domain.model.Estado;

public interface EstadoRepository {
	
	List<Estado> todos();
	Estado porId(Long id);
	Estado adicionar(Estado estado);
	void remover(Estado estado);

}
		