package br.com.kanjarana.kanjafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kanjarana.kanjafood.domain.model.Cozinha;
import br.com.kanjarana.kanjafood.domain.model.Restaurante;
import br.com.kanjarana.kanjafood.domain.repository.CozinhaRepository;
import br.com.kanjarana.kanjafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome) {
		return cozinhaRepository.nome(nome);
	}
	
	@GetMapping("/cozinhas/find-nome")
	public List<Cozinha> cozinhasPorNome2(String nome) {
		return cozinhaRepository.findTodasByNome(nome);
	}
	
	@GetMapping("/cozinhas/unica-find-nome")
	public Optional<Cozinha> cozinhaPorNome(String nome) {
		return cozinhaRepository.findByNome(nome);
	}
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, 
				BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping("/restaurantes/por-nome-cozinha_id")
	public List<Restaurante> restaurantesPorNomeCozinhaId(String nome, 
				Long cozinhaId) {
		//return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
		return restauranteRepository.consultaPorNome(nome, cozinhaId);
	}
	
	@GetMapping("/restaurantes/por-nome-e-taxa-frete")
	public List<Restaurante> restaurantesPorNomeTaxaFrete(String nome, BigDecimal taxaFreteInicial, 
				BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {
//		Specification<Restaurante> comFreteGratis = new RestauranteComFreteGratisEspec();
//		Specification<Restaurante> comNomeSemelhante = new RestauranteComNomeSemelhanteEspec(nome);
		
//		return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
		
//		return restauranteRepository.findAll(comFreteGratis()
//				.and(comNomeSemelhante(nome)));
		
		return restauranteRepository.findComFreteGratis(nome);
	}

}
