create table matricula(
	id bigint not null auto_increment,
    data_cadastro date not null,
    data_desativacao date not null,
    codigo_matriculo varchar(100),
    primary key(id)
) engine=InnoDB;