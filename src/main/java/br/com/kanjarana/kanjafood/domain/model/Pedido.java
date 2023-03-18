package br.com.kanjarana.kanjafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private BigDecimal subtotal;
	
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	@CreationTimestamp
	@Column(name = "data_criacao", columnDefinition = "datetime", nullable = false)
	private LocalDateTime dataCriacao;
	
	@Column(name = "data_confirmacao", columnDefinition = "datetime")	
	private LocalDateTime dataConfirmacao;
	
	@Column(name = "data_cancelamento", columnDefinition = "datetime")
	private LocalDateTime dataCancelamento;

	@Column(name = "data_entrega", columnDefinition = "datetime")
	private LocalDateTime dataEntrega;

	@Column(nullable = false, columnDefinition = "varchar(10)")
	private StatusPedido status;
	
	@Embedded
	private EnderecoEntrega enderecoEntrega;

	
	@ManyToOne	
	@JoinColumn(nullable = false)
	private Restaurante restaurante;
	

	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;
	
	
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;
	
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens = new ArrayList<>();

}














