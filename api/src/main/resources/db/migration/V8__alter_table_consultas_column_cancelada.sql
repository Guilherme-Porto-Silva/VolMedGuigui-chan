alter table consultas add cancelada tinyint;

update consultas set cancelada = 0;

alter table consultas modify cancelada tinyint not null;