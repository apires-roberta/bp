insert into ong (autenticado, email, nome, senha, telefone, usuario, cnpj) values 
(false, 'roberta2003p@gmail.com', 'Amigos do Bem', 'Senha123', '(11) 95393-8243', 'AmigosBem', '32357241000189');

insert into doador (autenticado, email, nome, senha, telefone, usuario, cpf) values
(false, 'JuanSilva@gmail.com', 'Juan da Silva', 'Senha456', '(11) 96676-8265', 'JuanSilva', '40238315061');

insert into campanha (nome_item, desc_campanha, data_criacao, ong_cod, nome_campanha, valor_necessario)
values ('Cadeira de rodas','Conseguir uma cadeira de rodas', '2022-04-20', 1, 'Cadeira de Rodas', 100.00),
('Vacinas', 'Conseguir vacinas para os necessitados', '2022-04-22', 1, 'Vacinas', 1000.00);

insert into doacao (data_doacao, doador_cod, ong_cod, campanha_id_campanha, valor_doacao)
values ('2022-04-23', 1, 1, 1, 55.23),
('2022-04-22 08:10:00', 1, 1, 2, 50.00),
('2022-04-22 23:10:45', 1, 1, 2, 10.00),
('2022-04-22 12:23:43', 1, 1, 2, 5.50),
('2022-04-22 14:43:57', 1, 1, 2, 23.10);