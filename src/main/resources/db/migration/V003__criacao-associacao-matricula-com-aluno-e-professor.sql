alter table aluno add column matricula_id bigint not null;

alter table aluno add constraint fk_matricula_aluno foreign key (matricula_id) references matricula (id);

alter table professor add column matricula_id bigint not null;

alter table professor add constraint fk_professor_matricula foreign key (matricula_id) references matricula(id);