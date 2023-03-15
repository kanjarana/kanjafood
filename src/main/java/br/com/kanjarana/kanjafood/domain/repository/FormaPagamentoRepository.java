package br.com.kanjarana.kanjafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kanjarana.kanjafood.domain.model.FormaPagamento;


public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}
