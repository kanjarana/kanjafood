package br.com.kanjarana.kanjafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kanjarana.kanjafood.domain.model.Cidade;


public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	
//	List<Cidade> listar();
//	Cidade buscar(Long id);
//	Cidade salvar(Cidade cidade);
//	void remover(Long id);
	
}	