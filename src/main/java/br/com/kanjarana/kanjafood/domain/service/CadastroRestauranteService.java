package br.com.kanjarana.kanjafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import br.com.kanjarana.kanjafood.domain.exception.EntidadeEmUsoException;
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
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Não existe cadastro de cozinha com o código: %d", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}

	public void excluir(Long restauranteId) {
			try {
				restauranteRepository.deleteById(restauranteId);
			}
			catch (DataIntegrityViolationException e) {
				throw new EntidadeEmUsoException(
						String.format("Restaurante de código %d não pode ser excluída, pois está em uso.", restauranteId));
			}
			catch (EmptyResultDataAccessException e) {
				throw new EntidadeNaoEncontradaException(
						String.format("Não existe um cadastro de restaurante com o código %d.", restauranteId));
			}
	}
	
}




