create database bp;
use bp;
create table ong(
	cod int identity(1,1) primary key,
	nome varchar(100) not null,
	cnpj char(14) not null,
	autenticado bit not null,
	email varchar(45) not null,
	senha varchar(16) not null,
	telefone char(15) not null,
	usuario varchar(20) not null,
	cep char(9) not null,
	numero int not null,
	foto_perfil binary
);

create table doador(
	cod int identity(1,1) primary key,
	nome varchar(100) not null,
	cpf char(14) not null,
	autenticado bit not null,
	email varchar(45) not null,
	senha varchar(16) not null,
	telefone char(15) not null,
	usuario varchar(20) not null,
	cep char(9) not null,
	numero int not null,
	foto_perfil binary
);


create table campanha(
	id_campanha int identity(1,1) primary key,
	data_criacao date not null,
	desc_campanha varchar(100) not null,
	nome_campanha varchar(45) not null,
	nome_item varchar(25) not null,
	valor_necessario decimal(8,2) not null,
	ong_cod int not null,
	FOREIGN KEY (ong_cod) REFERENCES ong(cod)
);

create table doacao(
	id_doacao int identity(1,1) primary key,
	data_doacao datetime not null,
	valor_doacao decimal(8,2) not null,
	fk_campanha int not null,
	doador_cod int not null,
	ong_cod int not null,
	FOREIGN KEY (ong_cod) REFERENCES ong(cod),
	FOREIGN KEY (doador_cod) REFERENCES doador(cod),
	FOREIGN KEY (fk_campanha) REFERENCES campanha(id_campanha)
);

create table inscricao(
	ong_cod int,
	doador_cod int,
	FOREIGN KEY (ong_cod) REFERENCES ong(cod),
	FOREIGN KEY (doador_cod) REFERENCES doador(cod),
	primary key(ong_cod,doador_cod)
);

create table feed(
	codigo int identity(1,1) primary key,
	data_publicacao datetime not null,
	descricao varchar(255) not null,
	foto_feed varbinary(max) not null,
	foto_perfil_ong varbinary(max) not null,
	ong_cod int not null,
	FOREIGN KEY (ong_cod) REFERENCES ong(cod)
);

create table notificacao_feed(
	id int identity(1,1) primary key,
	data_notificacao datetime not null,
	inscricao_ong_cod int not null,
	FOREIGN KEY (inscricao_ong_cod) REFERENCES ong(cod)
);

drop table inscricao;