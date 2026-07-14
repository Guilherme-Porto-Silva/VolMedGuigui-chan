create table pacientes(
    id bigint not null auto_increment,
    nome varchar(50) not null,
    email varchar(50) not null unique,
    telefone varchar(20) not null unique,
    cpf varchar(15) not null unique,
    logradouro varchar(50) not null,
    bairro varchar(50) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(50) not null,

    primary key(id)
);