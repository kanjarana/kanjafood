package br.com.kanjarana.kanjafood.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.com.kanjarana.kanjafood.domain.model.Restaurante;

public class RestauranteEspecsFactory {
	
	public static Specification<Restaurante> comFreteGratis() {
//		return new RestauranteComFreteGratisEspec();
		
		return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome) {
//		return new RestauranteComNomeSemelhanteEspec(nome);
		
		return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
	}
	
	

}
