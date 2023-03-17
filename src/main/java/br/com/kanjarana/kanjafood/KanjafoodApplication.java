package br.com.kanjarana.kanjafood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.kanjarana.kanjafood.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class KanjafoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanjafoodApplication.class, args);
	}

}
