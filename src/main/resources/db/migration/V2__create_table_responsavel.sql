create table responsavel(
    id int primary key identity,
    nome varchar(500) not null,
    cpf varchar(250) not null unique,
    email varchar(25) not null,
    senha varchar(25) not null,
    data_de_nascimento datetime not null,
    genero varchar(20) not null,
    foto text null,
)