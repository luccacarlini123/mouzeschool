alter table professor add endereco_cep varchar(50);
alter table professor add endereco_logradouro varchar(50);
alter table professor add endereco_numero varchar(50);
alter table professor add endereco_complemento varchar(50);
alter table professor add endereco_bairro varchar(50);
alter table professor add endereco_cidade_id bigint;

alter table aluno add endereco_cep varchar(50);
alter table aluno add endereco_logradouro varchar(50);
alter table aluno add endereco_numero varchar(50);
alter table aluno add endereco_complemento varchar(50);
alter table aluno add endereco_bairro varchar(50);
alter table aluno add endereco_cidade_id bigint;

alter table aluno add constraint fk_aluno_endereco_cidade_id foreign key(endereco_cidade_id) references cidade(id);
alter table professor add constraint fk_professor_endereco_cidade_id foreign key(endereco_cidade_id) references cidade(id);