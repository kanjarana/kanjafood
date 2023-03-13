package br.com.kanjarana.kanjafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.kanjarana.kanjafood.domain.model.Estado;
import br.com.kanjarana.kanjafood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public List<Estado> todos() {		
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

	@Override
	public Estado porId(Long id) {		
		return manager.find(Estado.class, id);
	}

	@Transactional
	@Override
	public Estado adicionar(Estado estado) {
		return manager.merge(estado);
	}

	@Transactional
	@Override
	public void remover(Estado estado) {
		estado = porId(estado.getId());
		manager.remove(estado);
		
	}

}
