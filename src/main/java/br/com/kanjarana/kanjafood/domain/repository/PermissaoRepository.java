package br.com.kanjarana.kanjafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kanjarana.kanjafood.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
