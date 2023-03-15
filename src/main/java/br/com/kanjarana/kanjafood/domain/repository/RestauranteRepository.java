package br.com.kanjarana.kanjafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kanjarana.kanjafood.domain.model.Restaurante;

public interface RestauranteRepository  extends JpaRepository<Restaurante, Long> {
	
//	List<Restaurante> listar();
//	Restaurante buscar(Long id);
//	Restaurante salvar(Restaurante restaurante);
//	void remover(Restaurante restaurante);
}
