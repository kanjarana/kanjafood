package br.com.kanjarana.kanjafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.kanjarana.kanjafood.api.model.CozinhasXmlWrapper;
import br.com.kanjarana.kanjafood.domain.exception.EntidadeEmUsoException;
import br.com.kanjarana.kanjafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.kanjarana.kanjafood.domain.model.Cozinha;
import br.com.kanjarana.kanjafood.domain.repository.CozinhaRepository;
import br.com.kanjarana.kanjafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	CozinhaRepository cozinhas;
	
	//@GetMapping(produces = ({MediaType.APPLICATION_JSON_VALUE})
	@GetMapping	
	public List<Cozinha> todas() {
		return cozinhas.todos();
	}
	
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE})
	public CozinhasXmlWrapper todasXml() {
		return new CozinhasXmlWrapper(todas());
	}
	
//	@GetMapping("/{cozinhaId}")
//	public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
//		return cozinhas.porId(id);		
//	}
	
//	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cozinhas.buscar(cozinhaId);
		
//		return 	ResponseEntity.status(HttpStatus.OK).body(cozinha);
//		return 	ResponseEntity.ok(cozinha);
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://api.kanjafood.local:8080/cozinhas");		
//		return ResponseEntity
//				.status(HttpStatus.FOUND)
//				.headers(headers)
//				.build();
		
		if (cozinha != null) {
			return ResponseEntity.ok(cozinha);			
		}
		
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha salvar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,  
			@RequestBody Cozinha cozinha) {
		
		Cozinha cozinhaAtual = cozinhas.buscar(cozinhaId);  // attached
		
		if (cozinhaAtual != null) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			
			cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
			return ResponseEntity.ok(cozinhaAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
		
		try {
			cadastroCozinha.excluir(cozinhaId);
			
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










