package br.com.kanjarana.kanjafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.kanjarana.kanjafood.KanjafoodApplication;
import br.com.kanjarana.kanjafood.domain.model.Cozinha;
import br.com.kanjarana.kanjafood.domain.repository.CozinhaRepository;

public class RemoverCozinhaMain {
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(KanjafoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		//CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepositoryImpl.class);
		CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);
		
		cozinhas.deleteById(cozinha.getId());
	}

}
