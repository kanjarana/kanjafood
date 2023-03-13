package br.com.kanjarana.kanjafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kanjarana.kanjafood.domain.repository.EstadoRepository;
import br.com.kanjarana.kanjafood.domain.model.Estado;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	EstadoRepository estados;
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public List<Estado> todos() {
		return estados.todos();
	}

}
