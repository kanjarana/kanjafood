
create table forma_pagamento (
	id bigint not null auto_increment, 
    descricao varchar(60) not null,         
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table grupo (
	id bigint not null auto_increment, 
    nome varchar(60) not null, 
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table grupo__permissao (
	grupo_id bigint not null, 
    permissao_id bigint not null,

	primary key (grupo_id, permissao_id)
) engine=InnoDB default charset=utf8mb4;

create table permissao (
	id bigint not null auto_increment, 
    descricao varchar(60) not null, 
    nome varchar(100) not null, 
    
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table produto (
	id bigint not null auto_increment,     
    descricao text not null, 
    nome varchar(80) not null, 
    preco decimal(10,2) not null, 
    ativo bit not null, 
    restaurante_id bigint not null, 
    	
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table restaurante (
	id bigint not null auto_increment, 
    nome varchar(80) not null, 
    taxa_frete decimal(10,2) not null, 
    data_atualizacao datetime not null, 
    data_cadastro datetime not null, 
        
    endereco_logradouro varchar(100), 
    endereco_numero varchar(20), 
    endereco_bairro varchar(60), 
    endereco_complemento varchar(60), 
    endereco_cep varchar(9), 
    endereco_cidade_id bigint,
    
    cozinha_id bigint not null, 
     
    primary key (id)
    ) engine=InnoDB default charset=utf8mb4;
    
create table restaurante__forma_pagamento (
	restaurante_id bigint not null, 
    forma_pagamento_id bigint not null,
    
    primary key (restaurante_id, forma_pagamento_id)
) engine=InnoDB default charset=utf8mb4;


create table usuario (
	id bigint not null auto_increment, 
	nome varchar(80) not null, 
    email varchar(80) not null, 
    senha varchar(255) not null, 
    data_cadastro datetime not null, 
    
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table usuario__grupo (
	usuario_id bigint not null, 
    grupo_id bigint not null,
    
    primary key(usuario_id, grupo_id)
) engine=InnoDB default charset=utf8mb4;



alter table grupo__permissao add constraint FK_grupo__permissao_grupo 
	foreign key (grupo_id) references grupo (id);

alter table grupo__permissao add constraint FK_grupo__permissao_permissao 
	foreign key (permissao_id) references permissao (id);

alter table produto add constraint FK_produto_restaurante 
	foreign key (restaurante_id) references restaurante (id);

alter table restaurante add constraint FK_restaurante_cozinha 
	foreign key (cozinha_id) references cozinha (id);

alter table restaurante add constraint FK_restaurante_cidade 
	foreign key (endereco_cidade_id) references cidade (id);

alter table restaurante__forma_pagamento add constraint FK_restaurante__forma_pagto_forma_pagto 
foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table restaurante__forma_pagamento add constraint FK_restaurante__forma_pagto_restaurante 
foreign key (restaurante_id) references restaurante (id);

alter table usuario__grupo add constraint FK_usuario__grupo_usuario
	foreign key (usuario_id) references usuario (id);

alter table usuario__grupo add constraint FK_usuario__grupo_grupo 
	foreign key (grupo_id) references grupo (id);

