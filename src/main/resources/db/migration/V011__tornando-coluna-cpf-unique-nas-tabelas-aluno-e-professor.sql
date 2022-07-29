alter table aluno drop column cpf;
alter table professor drop column cpf;

alter table aluno add cpf varchar(15) unique;
alter table professor add cpf varchar(15) unique;