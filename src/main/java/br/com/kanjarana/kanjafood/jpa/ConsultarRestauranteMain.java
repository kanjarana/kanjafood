package br.com.kanjarana.kanjafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.kanjarana.kanjafood.KanjafoodApplication;
import br.com.kanjarana.kanjafood.domain.model.Restaurante;
import br.com.kanjarana.kanjafood.domain.repository.RestauranteRepository;

public class ConsultarRestauranteMain {
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(KanjafoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> todos = restaurantes.todos();
		
		todos.forEach(r -> System.out.printf(  "%d - %s - Taxa frete: R$ %.2f Cozinha: %s\n", 
				r.getId(), r.getNome(), r.getTaxaFrete().floatValue(), r.getCozinha().getNome()));
	}

}
			//Taxa frete:R$ %.2f\n