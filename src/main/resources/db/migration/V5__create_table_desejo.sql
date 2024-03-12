create table desejo(
    id int primary key identity,
    titulo varchar(100) not null,
    descricao varchar(250),
    valor float,
    id_crianca int not null,
    foreign key (id_crianca) references crianca(id), 
    data_de_criacao datetime not null
)