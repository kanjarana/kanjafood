package br.com.kanjarana.kanjafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.kanjarana.kanjafood.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, 
			BigDecimal taxaFreteInicial, 
			BigDecimal taxaFreteFinal);

}