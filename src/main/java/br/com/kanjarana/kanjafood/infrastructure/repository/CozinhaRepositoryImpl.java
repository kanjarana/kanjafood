package br.com.kanjarana.kanjafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import br.com.kanjarana.kanjafood.domain.model.Cozinha;
import br.com.kanjarana.kanjafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public List<Cozinha> listar() {		
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

	@Override
	public Cozinha buscar(Long id) {		
		return manager.find(Cozinha.class, id);
	}

	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Cozinha cozinha = buscar(id);
		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(cozinha);		
	}

}
