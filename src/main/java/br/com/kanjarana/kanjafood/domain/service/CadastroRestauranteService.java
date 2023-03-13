package br.com.kanjarana.kanjafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.kanjarana.kanjafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.kanjarana.kanjafood.domain.model.Cozinha;
import br.com.kanjarana.kanjafood.domain.model.Restaurante;
import br.com.kanjarana.kanjafood.domain.repository.CozinhaRepository;
import br.com.kanjarana.kanjafood.domain.repository.RestauranteRepository;

@Controller
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {

		Long cozinhaId = restaurante.getCozinha().getId();		
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com o código: %d", 
							cozinhaId));
		}
		
		return restauranteRepository.salvar(restaurante);
	}
	
}




