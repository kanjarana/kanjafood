package br.com.kanjarana.kanjafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.kanjarana.kanjafood.domain.exception.EntidadeEmUsoException;
import br.com.kanjarana.kanjafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.kanjarana.kanjafood.domain.model.Restaurante;
import br.com.kanjarana.kanjafood.domain.repository.CozinhaRepository;
import br.com.kanjarana.kanjafood.domain.repository.RestauranteRepository;
import br.com.kanjarana.kanjafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	@Autowired
	CadastroRestauranteService cadastroRestaurante;
	
	@GetMapping
	public List<Restaurante> listar() {
		List<Restaurante> restaurantes = restauranteRepository.findAll();
		
		return restaurantes;
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
		
		Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
		
		if (restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = cadastroRestaurante.salvar(restaurante);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante); 
		}
		catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, 
			@RequestBody Restaurante restaurante) {
		try {
			Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
			
			if (restauranteAtual.isPresent()) {				
				BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");	
				
				Restaurante restauranteSlavo = cadastroRestaurante.salvar(restauranteAtual.get());
				
				return ResponseEntity.ok(restauranteSlavo); 
			}
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, 
			@RequestBody Map<String, Object> campos) {
		
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
		if (restauranteAtual.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, restauranteAtual.get());
		
		return atualizar(restauranteId, restauranteAtual.get());	
	}

	

	private void merge(Map<String, Object> dadosOrigem, Restaurante destino) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante origem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		
		
		dadosOrigem.forEach((nomeAtributo, valorAtributo) -> {
			System.out.println(nomeAtributo + " = " + valorAtributo);
			
			Field field = ReflectionUtils.findField(Restaurante.class, nomeAtributo);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, origem);
			
			//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
			
			ReflectionUtils.setField(field, destino, novoValor);
			
		});		
	}
	
	@DeleteMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> remover(@PathVariable Long restauranteId) {
		
		try {
			cadastroRestaurante.excluir(restauranteId);
			
			return ResponseEntity.noContent().build();
		}
		catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
		catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
}





