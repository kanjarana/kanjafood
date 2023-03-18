package br.com.kanjarana.kanjafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kanjarana.kanjafood.domain.model.Restaurante;

public interface RestauranteRepository  
			extends CustomJpaRepository<Restaurante, Long>, 
					RestauranteRepositoryQueries,
					JpaSpecificationExecutor<Restaurante> {
	
	@Query("select distinct r from  Restaurante r left join r.cozinha " /* + "join fetch r.formasPagamento"*/)
	List<Restaurante> findAll();
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	//@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultaPorNome(String nome, @Param("id") Long cozinha);
	
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
//	List<Restaurante> listar();
//	Restaurante buscar(Long id);
//	Restaurante salvar(Restaurante restaurante);
//	void remover(Restaurante restaurante);
}
