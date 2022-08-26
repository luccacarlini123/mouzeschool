create table foto_aluno (

	aluno_id bigint not null,
	nome_arquivo varchar(200) not null,
	descricao varchar(200),
	content_type varchar(80) not null,
	tamanho int not null,
	
	primary key(aluno_id),
	constraint fk_foto_aluno_aluno foreign key (aluno_id) references aluno(id)

)engine=InnoDB default charset=utf8;