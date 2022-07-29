    create table aluno (
       id bigint not null auto_increment,
        cpf varchar(15),
        data_nascimento date,
        email varchar(150),
        nome varchar(150),
        rg varchar(15),
        telefone varchar(20),
        primary key (id)
    ) engine=InnoDB;

    create table alunos_turma (
       turma_id bigint not null,
        aluno_id bigint not null
    ) engine=InnoDB;

    create table materia (
       id bigint not null auto_increment,
        codigo varchar(20),
        data_criacao date,
        grau_ensino varchar(20),
        nome varchar(40),
        serie_ensino varchar(20),
        status_materia varchar(20),
        primary key (id)
    ) engine=InnoDB;

    create table nota (
       id bigint not null auto_increment,
        bimestre smallint,
        data_cadastro date,
        valor double precision,
        aluno_id bigint not null,
        materia_id bigint not null,
        turma_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table professor (
       id bigint not null auto_increment,
        cpf varchar(15),
        data_nascimento date,
        email varchar(150),
        nome varchar(150),
        rg varchar(15),
        telefone varchar(20),
        primary key (id)
    ) engine=InnoDB;

    create table turma (
       id bigint not null auto_increment,
        data_criacao date not null,
        grau_ensino varchar(20),
        nome varchar(40),
        serie_ensino varchar(20),
        status_turma varchar(20),
        primary key (id)
    ) engine=InnoDB;

    create table turma_materia_professor (
       turma_id bigint not null,
        materia_id bigint not null,
        professor_id bigint,
        primary key (materia_id, turma_id)
    ) engine=InnoDB;

    alter table alunos_turma 
       add constraint fk_aluno_alunos_turma 
       foreign key (aluno_id) 
       references aluno (id);

    alter table alunos_turma 
       add constraint fk_turma_alunos_turma
       foreign key (turma_id) 
       references turma (id);

    alter table nota 
       add constraint fk_aluno_nota 
       foreign key (aluno_id) 
       references aluno (id);

    alter table nota 
       add constraint fk_materia_nota 
       foreign key (materia_id) 
       references materia (id);

    alter table nota 
       add constraint fk_turma_nota 
       foreign key (turma_id) 
       references turma (id);

    alter table turma_materia_professor 
       add constraint fk_turma_turma_materia_professor 
       foreign key (turma_id) 
       references turma (id);

    alter table turma_materia_professor 
       add constraint fk_materia_turma_materia_professor
       foreign key (materia_id) 
       references materia (id);

    alter table turma_materia_professor 
       add constraint fk_professor_turma_materia_professor
       foreign key (professor_id) 
       references professor (id);