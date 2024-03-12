  create table usuario(
    id int primary key identity,
    email varchar(250) not null unique,
    senha varchar(300) not null,
    id_responsavel int,
    foreign key (id_responsavel) references responsavel(id), 
    id_crianca int,
    foreign key (id_crianca) references crianca(id),
    role varchar(25) not null
)