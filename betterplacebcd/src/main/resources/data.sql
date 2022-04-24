insert into ong (cnpj_ong, nome_ong) values ('123', 'Amigos do Bem');
insert into doador (cpf, nome_doador) values ('123', 'Juan Silva');
insert into vakinha (nome_item, desc_vakinha, data_criacao, fk_ong, nome_vakinha, valor_necessario)
values ('Cadeira de rodas','Conseguir uma cadeira de rodas', '2022-04-20', 1, 'Cadeira de Rodas', 100.00),
('Vacinas', 'Conseguir vacinas para os necessitados', '2022-04-22', 1, 'Vacinas', 1000.00);
insert into doacao (data_doacao, fk_doador, fk_ong, fk_vakinha, valor_doacao)
values ('2022-04-23', 1, 1, 1, 55.23),
('2022-04-22 08:10:00', 1, 1, 2, 50.00),
('2022-04-22 23:10:45', 1, 1, 2, 10.00),
('2022-04-22 12:23:43', 1, 1, 2, 5.50),
('2022-04-22 14:43:57', 1, 1, 2, 23.10);