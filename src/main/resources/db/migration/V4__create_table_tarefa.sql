create table tarefa(
    id int primary key identity,
    titulo varchar(100) not null,
    descricao varchar(250),
    data_de_criacao datetime not null,
    data_limite datetime not null,
    hora_limite datetime not null,
    valor float not null,
    id_crianca int not null,
    foreign key (id_crianca) references crianca(id) 
)