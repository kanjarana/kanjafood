package br.com.kanjarana.kanjafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import br.com.kanjarana.kanjafood.domain.model.Cozinha;
import br.com.kanjarana.kanjafood.domain.repository.CozinhaRepository;
import br.com.kanjarana.kanjafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	//@GetMapping(produces = ({MediaType.APPLICATION_JSON_VALUE})
	@GetMapping	
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}
	
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE})
	public CozinhasXmlWrapper todasXml() {
		return new CozinhasXmlWrapper(listar());
	}
	
//	@GetMapping("/{cozinhaId}")
//	public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
//		return cozinhas.porId(id);		
//	}
	
	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable Long cozinhaId) {
		return cadastroCozinha.buscarOuFalhar(cozinhaId);
	}
	
	
/*
	//	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://api.kanjafood.local:8080/cozinhas");		
//		return ResponseEntity
//				.status(HttpStatus.FOUND)
//				.headers(headers)
//				.build();
		
		if (cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());			
		}
		
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();		
	}	
*/	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}

	
	@PutMapping("/{cozinhaId}")
	public Cozinha atualizar(@PathVariable Long cozinhaId,  
				@RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);  
		
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			
		return cadastroCozinha.salvar(cozinhaAtual);
	}
	
/*	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,  
				@RequestBody Cozinha cozinha) {
		
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);  // attached
		
		if (cozinhaAtual.isPresent()) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
			
			Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());
			return ResponseEntity.ok(cozinhaSalva);
		}		
		return ResponseEntity.notFound().build();
	}
*/	
	
	
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {		
		cadastroCozinha.excluir(cozinhaId);		
	}
	
	/*
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
	*/

}










