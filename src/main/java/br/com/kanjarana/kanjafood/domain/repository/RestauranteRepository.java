package br.com.kanjarana.kanjafood.domain.repository;

import java.util.List;

import br.com.kanjarana.kanjafood.domain.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> todos();
	Restaurante buscar(Long id);
	Restaurante salvar(Restaurante restaurante);
	void remover(Long id);
}