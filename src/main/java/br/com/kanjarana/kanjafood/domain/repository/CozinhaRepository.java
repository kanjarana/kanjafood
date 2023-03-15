package br.com.kanjarana.kanjafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kanjarana.kanjafood.domain.model.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	
	
//	List<Cozinha> listar();
//	Cozinha buscar(Long id);
//	Cozinha salvar(Cozinha cozinha);
//	void remover(Long id);
	
}
