package br.com.kanjarana.kanjafood.domain.repository;

import java.util.List;

import br.com.kanjarana.kanjafood.domain.model.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> listar();
	Permissao buscar(Long id);
	Permissao salvar(Permissao permissao);
	void remover(Permissao permissao);
}
