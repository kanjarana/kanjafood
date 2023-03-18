    create table pedido (
       id bigint not null auto_increment,
        data_criacao datetime not null,
        status varchar(10) not null,
        
        subtotal decimal(10,2) not null,
        taxa_frete decimal(10,2) not null,
        valor_total decimal(10,2) not null,
        
        data_cancelamento datetime,
        data_confirmacao datetime,        
        data_entrega datetime,
        
        endereco_bairro varchar(255) not null,
        endereco_cep varchar(255) not null,
        endereco_complemento varchar(255),
        endereco_logradouro varchar(255) not null,
        endereco_numero varchar(255) not null,
        
        endereco_cidade_id bigint not null,
        
        usuario_cliente_id bigint not null,        
        forma_pagamento_id bigint not null,
        restaurante_id bigint not null,
        
        primary key (id)
    ) engine=InnoDB default charset=utf8mb4;

    create table item_pedido (
       id bigint not null,
        observacao varchar(255),
        preco_total decimal(10,2) not null,
        preco_unitario decimal(10,2) not null,
        quantidade integer not null,
        pedido_id bigint not null,
        produto_id bigint not null,
        
        primary key (id),
		unique key uk_item_pedido_produto (pedido_id, produto_id)
    ) engine=InnoDB default charset=utf8mb4;


    alter table item_pedido add constraint FK_item_pedido_pedido
       foreign key (pedido_id) references pedido (id);

    alter table item_pedido add constraint FK_item_pedido_produto 
       foreign key (produto_id) references produto (id);


    alter table pedido 
		add constraint FK_pedido_restaurante     foreign key (restaurante_id) references restaurante (id),
        add constraint FK_pedido_usuario_cliente foreign key (usuario_cliente_id) references usuario (id),
        add constraint FK_pedido_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id),
        add constraint FK_pedido_cidade          foreign key (endereco_cidade_id) references cidade (id)
	;

    