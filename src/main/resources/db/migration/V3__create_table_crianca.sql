create table crianca(
    id int primary key identity,
    nome varchar(500) not null,
    data_de_nascimento datetime not null,
    genero varchar(20) not null,
    saldo float not null,
    email varchar(25) not null,
    senha varchar(25) not null,
    apelido varchar(250),
    foto text,
    id_responsavel int not null ,
    foreign key (id_responsavel) references responsavel(id)
)