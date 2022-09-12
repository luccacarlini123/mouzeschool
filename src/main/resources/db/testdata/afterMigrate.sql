set foreign_key_checks = 0;

delete from aluno;
delete from alunos_turma;
delete from materia;
delete from nota;
delete from professor;
delete from turma;
delete from turma_materia_professor;
delete from matricula;
delete from cidade;
delete from estado;
delete from foto_professor;
delete from foto_aluno;

set foreign_key_checks = 1;

alter table aluno auto_increment = 1;
alter table materia auto_increment = 1;
alter table nota auto_increment = 1;
alter table professor auto_increment = 1;
alter table turma auto_increment = 1;
alter table cidade auto_increment = 1;
alter table estado auto_increment = 1;

insert into estado(id, nome, sigla) values(1, 'Rio de Janeiro', 'RJ');

insert into cidade(id, nome, estado_id) values(1, 'Paracambi', 1);

insert into matricula(id, data_cadastro, codigo_matricula, status) value (1, '2022-05-25', '202201334494', 'ATIVADA');
insert into matricula(id, data_cadastro, codigo_matricula, status) value (2, '2022-05-25', '202201333214', 'ATIVADA');
insert into matricula(id, data_cadastro, codigo_matricula, status) value (3, '2022-05-25', '202200000494', 'ATIVADA');
insert into matricula(id, data_cadastro, codigo_matricula, status) value (4, '2022-05-25', '202201236594', 'ATIVADA');
insert into matricula(id, data_cadastro, codigo_matricula, status) value (5, '2022-05-25', '202201330124', 'ATIVADA');
insert into matricula(id, data_cadastro, codigo_matricula, status) value (6, '2022-05-25', '202201385044', 'ATIVADA');

insert into aluno(id, nome, data_nascimento, email, rg, cpf, telefone, matricula_id) values(1, 'Lucca', '1998-04-06', 'lucca.carlini18@gmail.com', '12365482', '16976587764', '978885443', 1);
insert into aluno(id, nome, data_nascimento, email, rg, cpf, telefone, matricula_id) values(2, 'Pedro', '2003-04-06', 'lucca.carlini1998@gmail.com', '30265874', '16976123764', '978125443', 2);
insert into aluno(id, nome, data_nascimento, email, rg, cpf, telefone, matricula_id) values(3, 'Carlo', '2004-04-10', 'lucca.carlini1998@gmail.com', '02366589', '16976996064', '966685443', 3);

insert into professor(id, nome, data_nascimento, email, rg, cpf, telefone, matricula_id) values(1, 'Junior', '1976-04-06', 'junior@gmail.com', '12365696', '16971227764', '978883743', 4);
insert into professor(id, nome, data_nascimento, email, rg, cpf, telefone, matricula_id) values(2, 'Erick', '1965-04-06', 'erick@gmail.com', '30260000', '16976321764', '978125496', 5);
insert into professor(id, nome, data_nascimento, email, rg, cpf, telefone, matricula_id) values(3, 'Carola', '1980-04-06', 'carola@gmail.com', '02396587', '36250096064', '966612343', 6);

insert into turma(id, nome, data_criacao, status_turma, grau_ensino, serie_ensino) values(1, 'Turma 3001', '2022-01-09', 'ATIVADA', 'ENSINO_MEDIO', 'TERCEIRO_ANO');
insert into turma(id, nome, data_criacao, status_turma, grau_ensino, serie_ensino) values(2, 'Turma 3002', '2022-01-09', 'ATIVADA', 'ENSINO_MEDIO', 'TERCEIRO_ANO');
insert into turma(id, nome, data_criacao, status_turma, grau_ensino, serie_ensino) values(3, 'Turma 3003', '2022-01-09', 'DESATIVADA', 'ENSINO_MEDIO', 'TERCEIRO_ANO');

insert into alunos_turma(turma_id, aluno_id) values(1, 1);
insert into alunos_turma(turma_id, aluno_id) values(2, 2);

insert into materia(id, data_criacao, nome, codigo, status_materia, grau_ensino, serie_ensino) values(1, '2022-01-12', 'Matemática', 'MAT202201', 'ATIVADA', 'ENSINO_MEDIO', 'TERCEIRO_ANO');
insert into materia(id, data_criacao, nome, codigo, status_materia, grau_ensino, serie_ensino) values(2, '2022-01-12', 'Portugês', 'POR202201', 'ATIVADA', 'ENSINO_MEDIO', 'TERCEIRO_ANO');
insert into materia(id, data_criacao, nome, codigo, status_materia, grau_ensino, serie_ensino) values(3, '2022-01-12', 'Geografia', 'GEO202201', 'ATIVADA', 'ENSINO_MEDIO', 'SEGUNDO_ANO');

insert into turma_materia_professor(turma_id, materia_id, professor_id) values(1,1,null);
insert into turma_materia_professor(turma_id, materia_id, professor_id) values(2,1,1);

insert into turma_materia_professor(turma_id, materia_id, professor_id) values(1,2,null);
insert into turma_materia_professor(turma_id, materia_id, professor_id) values(2,2,1);

insert into nota(id, bimestre, valor, aluno_id, materia_id, turma_id, data_cadastro) values(1, 1, 9.6, 1, 1, 1, '2022-02-17');
insert into nota(id, bimestre, valor, aluno_id, materia_id, turma_id, data_cadastro) values(2, 2, 9.6, 1, 1, 1, '2022-02-17');
insert into nota(id, bimestre, valor, aluno_id, materia_id, turma_id, data_cadastro) values(3, 3, 9.6, 1, 1, 1, '2022-02-17');
insert into nota(id, bimestre, valor, aluno_id, materia_id, turma_id, data_cadastro) values(4, 4, 9.6, 1, 1, 1, '2022-02-17');

insert into nota(id, bimestre, valor, aluno_id, materia_id, turma_id, data_cadastro) values(6, 1, 8, 1, 2, 1, '2022-02-17');
insert into nota(id, bimestre, valor, aluno_id, materia_id, turma_id, data_cadastro) values(8, 2, 10, 1, 2, 1, '2022-02-17');
insert into nota(id, bimestre, valor, aluno_id, materia_id, turma_id, data_cadastro) values(9, 3, 9, 1, 2, 1, '2022-02-17');
insert into nota(id, bimestre, valor, aluno_id, materia_id, turma_id, data_cadastro) values(10, 4, 7, 1, 2, 1, '2022-02-17');

insert into nota(id, bimestre, valor, aluno_id, materia_id, turma_id, data_cadastro) values(7, 1, 10, 2, 3, 2, '2022-02-17');












































