package br.com.kanjarana.kanjafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.kanjarana.kanjafood.domain.exception.EntidadeEmUsoException;
import br.com.kanjarana.kanjafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.kanjarana.kanjafood.domain.model.Estado;
import br.com.kanjarana.kanjafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_EM_USO 
			= "Estado de código %d não pode ser removido, pois está em uso";
	private static final String MSG_ESTADO_NAO_ENCONTRADO 
			= "Não existe um cadastro de estado com código %d";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}
	
	
	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId)));
	}
	
}
