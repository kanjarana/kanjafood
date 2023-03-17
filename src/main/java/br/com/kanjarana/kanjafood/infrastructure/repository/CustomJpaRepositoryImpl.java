package br.com.kanjarana.kanjafood.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import br.com.kanjarana.kanjafood.domain.repository.CustomJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
		implements CustomJpaRepository<T, ID>{
	
	private EntityManager manager;

	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		
		manager = entityManager;
	}


	@Override
	public Optional<T> buscarPrimeiro() {
		
		String jpql = "from " + getDomainClass().getName();
		
		TypedQuery<T> query = manager.createQuery(jpql, getDomainClass())
				.setMaxResults(1);
		 
		
		T entity = query.getSingleResult();
		
		return Optional.ofNullable(entity);
	}
	

}
