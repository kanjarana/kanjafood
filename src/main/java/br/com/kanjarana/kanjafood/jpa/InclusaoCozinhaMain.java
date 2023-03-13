package br.com.kanjarana.kanjafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.kanjarana.kanjafood.KanjafoodApplication;
import br.com.kanjarana.kanjafood.domain.model.Cozinha;
import br.com.kanjarana.kanjafood.domain.repository.CozinhaRepository;
import br.com.kanjarana.kanjafood.infrastructure.repository.CozinhaRepositoryImpl;

public class InclusaoCozinhaMain {
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(KanjafoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepositoryImpl.class);
		
		Cozinha cozinha = new Cozinha();
		Cozinha cozinha2 = new Cozinha();
		cozinha.setNome("Japonesa");
		cozinha2.setNome("Árabe");
		
		cozinhas.salvar(cozinha);
		cozinhas.salvar(cozinha2);
	}

}
