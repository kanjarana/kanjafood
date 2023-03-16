package br.com.kanjarana.kanjafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kanjarana.kanjafood.domain.model.Cozinha;


public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	
	List<Cozinha> nome(String nome);
	
	List<Cozinha> findTodasByNome(String nome);
	
	Optional<Cozinha> findByNome(String nome);
	

	//public List<Cozinha> findByNome(String nome);
	
	
	
//	List<Cozinha> listar();
//	Cozinha buscar(Long id);
//	Cozinha salvar(Cozinha cozinha);
//	void remover(Long id);
	
}
