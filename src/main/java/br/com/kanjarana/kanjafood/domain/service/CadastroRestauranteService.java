package br.com.kanjarana.kanjafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;

import br.com.kanjarana.kanjafood.domain.exception.EntidadeEmUsoException;
import br.com.kanjarana.kanjafood.domain.exception.RestauranteNaoEncontradoException;
import br.com.kanjarana.kanjafood.domain.model.Cozinha;
import br.com.kanjarana.kanjafood.domain.model.Restaurante;
import br.com.kanjarana.kanjafood.domain.repository.RestauranteRepository;

@Controller
public class CadastroRestauranteService {
	
	private static final String MSG_RESTAURANTE_EM_USO 
			= "Restaurante de código %d não pode ser excluída, pois está em uso.";


	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	CadastroCozinhaService cadastroCozinha;
	
	public Restaurante salvar(Restaurante restaurante) {

		Long cozinhaId = restaurante.getCozinha().getId();		

		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
		
//		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
//				.orElseThrow(() -> new EntidadeNaoEncontradaException(
//						String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
//		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}

	public void excluir(Long restauranteId) {
			try {
				restauranteRepository.deleteById(restauranteId);
			}
			catch (DataIntegrityViolationException e) {
				throw new EntidadeEmUsoException(
						String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
			}
			catch (EmptyResultDataAccessException e) {
				throw new RestauranteNaoEncontradoException(restauranteId);
			}
	}
	
	
	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
	}
	
}







