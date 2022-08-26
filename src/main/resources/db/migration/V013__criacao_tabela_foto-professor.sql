create table foto_professor (

	professor_id bigint not null,
	nome_arquivo varchar(200) not null,
	descricao varchar(200),
	content_type varchar(80) not null,
	tamanho int not null,
	
	primary key(professor_id),
	constraint fk_foto_professor_professor foreign key (professor_id) references professor(id)

)engine=InnoDB default charset=utf8;