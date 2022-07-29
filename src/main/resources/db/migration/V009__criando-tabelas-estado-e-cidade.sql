create table estado (
	id bigint not null auto_increment,
    sigla char(5) not null,
    nome varchar(100) not null,
    primary key(id)
);

create table cidade (
	id bigint not null auto_increment,
    nome varchar(100) not null,
    estado_id bigint not null,
    primary key(id),
    constraint fk_cidade_estado_id  foreign key (estado_id) references estado(id)
);