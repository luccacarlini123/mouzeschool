alter table matricula drop column codigo_matriculo;
alter table matricula add column codigo_matricula varchar(100) unique;