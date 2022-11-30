import mysql.connector
mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="bproot",
  database="bp"
)
mycursor = mydb.cursor()
sqlExecutar="""
CREATE PROCEDURE proc_estado(in estado char(2))
BEGIN
	declare cepInicial,cepFinal int;
	if estado='SP' then
        set cepInicial=1000000;
        set cepFinal=19999999;
    elseif estado='RJ' then
        set cepInicial=20000000;
        set cepFinal=28999999;
    elseif estado='ES' then
        set cepInicial=29000000;
        set cepFinal=29999999;
    elseif estado='MG' then
        set cepInicial=30000000;
        set cepFinal=39999999;
    elseif estado='BA' then
        set cepInicial=40000000;
        set cepFinal=48999999;
    elseif estado='SE' then
        set cepInicial=49000000;
        set cepFinal=49999999;
    elseif estado='PE' then
        set cepInicial=50000000;
        set cepFinal=56999999;
    elseif estado='AL' then
        set cepInicial=57000000;
        set cepFinal=57999999;
    elseif estado='PB' then
        set cepInicial=58000000;
        set cepFinal=58999999;
    elseif estado='RN' then
        set cepInicial=59000000;
        set cepFinal=59999999;
    elseif estado='CE' then
        set cepInicial=60000000;
        set cepFinal=63999999;
    elseif estado='PI' then
        set cepInicial=64000000;
        set cepFinal=64999999;
    elseif estado='MA' then
        set cepInicial=65000000;
        set cepFinal=65999999;
    elseif estado='PA' then
        set cepInicial=66000000;
        set cepFinal=68899999;
    elseif estado='AP' then
        set cepInicial=68900000;
        set cepFinal=68999999;
    elseif estado='AM' then
        set cepInicial=69400000;
        set cepFinal=69899999;
    elseif estado='RR' then
        set cepInicial=69300000;
        set cepFinal=69389999;
    elseif estado='AC' then
        set cepInicial=69900000;
        set cepFinal=69999999;
    elseif estado='DF' then
        set cepInicial=70000000;
        set cepFinal=73699999;
    elseif estado='GO' then
        set cepInicial=72800000;
        set cepFinal=76799999;
    elseif estado='TO' then
        set cepInicial=77000000;
        set cepFinal=77995999;
    elseif estado='MT' then
        set cepInicial=78000000;
        set cepFinal=78899999;
    elseif estado='RO' then
        set cepInicial=78900000;
        set cepFinal=78999999;
    elseif estado='MS' then
        set cepInicial=79000000;
        set cepFinal=79999999;
    elseif estado='PR' then
        set cepInicial=80000000;
        set cepFinal=87999999;
    elseif estado='SC' then
        set cepInicial=88000000;
        set cepFinal=89999999;
    elseif estado='RS' then
        set cepInicial=90000000;
        set cepFinal=99999999;
	END IF;
	SELECT "Doador" as tipo, count(*) as quantidade, date(data_criacao_conta) FROM doador where cep between cepInicial and cepFinal and data_criacao_conta BETWEEN DATE_ADD(CURRENT_DATE(), INTERVAL -6 DAY) AND date(CURRENT_DATE()) group by date(data_criacao_conta)
	union
	SELECT "Ong", count(*), date(data_criacao_conta) FROM ong where cep between cepInicial and cepFinal and data_criacao_conta BETWEEN DATE_ADD(CURRENT_DATE(), INTERVAL -6 DAY) AND date(CURRENT_DATE()) group by date(data_criacao_conta)
	union
	SELECT "Doacao", COUNT(*), date(data_doacao) FROM doacao as d inner join doador as c on d.doador_cod=c.cod  where c.cep between cepInicial and cepFinal and data_doacao BETWEEN DATE_ADD(CURRENT_DATE(), INTERVAL -6 DAY) AND date(CURRENT_DATE()) group by date(data_doacao)
	union
	SELECT "Campanha", COUNT(*), date(data_criacao) FROM campanha as c inner join ong as o on c.ong_cod=o.cod  where o.cep between cepInicial and cepFinal and data_criacao BETWEEN DATE_ADD(CURRENT_DATE(), INTERVAL -6 DAY) AND date(CURRENT_DATE()) group by date(data_criacao);
END
"""

mycursor.execute(sqlExecutar)

sqlExecutar="""
CREATE PROCEDURE proc_doacoes_semana(in mes int, in campanha int)
BEGIN
	declare s1,a1,s2,a2,s3,a3,s4,a4,s5,a5 varchar(8);
    if mes=1 then
		set s1="20220101";
        set a1="20220107";
        set s2="20220108";
        set a2="20220114";
        set s3="20220115";
        set a3="20220121";
        set s4="20220122";
        set a4="20220128";
        set s5="20220129";
        set a5="20220131";
	elseif mes=2 then
		set s1="20220201";
        set a1="20220207";
        set s2="20220208";
        set a2="20220214";
        set s3="20220215";
        set a3="20220221";
        set s4="20220222";
        set a4="20220228";
        set s5="20220229";
        set a5="20220231";
	elseif mes=3 then
		set s1="20220301";
        set a1="20220307";
        set s2="20220308";
        set a2="20220314";
        set s3="20220315";
        set a3="20220321";
        set s4="20220322";
        set a4="20220328";
        set s5="20220329";
        set a5="20220331";
	elseif mes=04 then
		set s1="20220401";
        set a1="20220407";
        set s2="20220408";
        set a2="20220414";
        set s3="20220415";
        set a3="20220421";
        set s4="20220422";
        set a4="20220428";
        set s5="20220429";
        set a5="20220430";
	elseif mes=05 then
		set s1="20220501";
        set a1="20220507";
        set s2="20220508";
        set a2="20220514";
        set s3="20220515";
        set a3="20220521";
        set s4="20220522";
        set a4="20220528";
        set s5="20220529";
        set a5="20220531";
	elseif mes=06 then
		set s1="20220601";
        set a1="20220607";
        set s2="20220608";
        set a2="20220614";
        set s3="20220615";
        set a3="20220621";
        set s4="20220622";
        set a4="20220628";
        set s5="20220629";
        set a5="20220631";
    elseif mes=7 then
		set s1="20220701";
        set a1="20220707";
        set s2="20220708";
        set a2="20220714";
        set s3="20220715";
        set a3="20220721";
        set s4="20220722";
        set a4="20220728";
        set s5="20220729";
        set a5="20220731";
	elseif mes=8 then
		set s1="20220801";
        set a1="20220807";
        set s2="20220808";
        set a2="20220814";
        set s3="20220815";
        set a3="20220821";
        set s4="20220822";
        set a4="20220828";
        set s5="20220829";
        set a5="20220831";
	elseif mes=9 then
		set s1="20220901";
        set a1="20220907";
        set s2="20220908";
        set a2="20220914";
        set s3="20220915";
        set a3="20220921";
        set s4="20220922";
        set a4="20220928";
        set s5="20220929";
        set a5="20220930";
	elseif mes=10 then
		set s1="20221001";
        set a1="20221007";
        set s2="20221008";
        set a2="20221014";
        set s3="20221015";
        set a3="20221021";
        set s4="20221022";
        set a4="20221028";
        set s5="20221029";
        set a5="20221031";
	elseif mes=11 then
		set s1="20221101";
        set a1="20221107";
        set s2="20221108";
        set a2="20221114";
        set s3="20221115";
        set a3="20221121";
        set s4="20221122";
        set a4="20221128";
        set s5="20221129";
        set a5="20221130";
	elseif mes=12 then
		set s1="20221201";
        set a1="20221207";
        set s2="20221208";
        set a2="20221214";
        set s3="20221215";
        set a3="20221221";
        set s4="20221222";
        set a4="20221228";
        set s5="20221229";
        set a5="20221231";
	end if;
		select sum(case when data_doacao between s1 and a1 then 1 else 0 end) AS 's_um',
			sum(case when data_doacao between s2 and a2 then 1 else 0 end) AS 's_dois',
			sum(case when data_doacao between s3 and a3 then 1 else 0 end) AS 's_tres',
			sum(case when data_doacao between s4 and a4 then 1 else 0 end) AS 's_quatro',
			sum(case when data_doacao between s5 and a5 then 1 else 0 end) AS 's_cinco'
            from doacao where campanha_id_campanha=campanha;
END
"""

mycursor.execute(sqlExecutar)

sqlExecutar="""
CREATE PROCEDURE proc_doacoes_dia(in mes int, in campanha int)
BEGIN
	declare diaInicial, diaFinal varchar(8);
    if mes=1 then
		set diaInicial="20220101";
        set diaFinal="20220131";
	elseif mes=2 then
		set diaInicial="20220201";
        set diaFinal="20220228";
	elseif mes=3 then
		set diaInicial="20220301";
        set diaFinal="20220331";
	elseif mes=4 then
		set diaInicial="20220401";
        set diaFinal="20220430";
	elseif mes=5 then
		set diaInicial="20220501";
        set diaFinal="20220531";
	elseif mes=6 then
		set diaInicial="20220601";
        set diaFinal="20220630";
    elseif mes=7 then
		set diaInicial="20220701";
        set diaFinal="20220731";
	elseif mes=8 then
		set diaInicial="20220801";
        set diaFinal="20220831";
	elseif mes=9 then
		set diaInicial="20220901";
        set diaFinal="20220930";
	elseif mes=10 then
		set diaInicial="20221001";
        set diaFinal="20221031";
	elseif mes=11 then
		set diaInicial="20221101";
        set diaFinal="20221130";
	elseif mes=12 then
		set diaInicial="20221201";
        set diaFinal="20221231";
	end if;
		SELECT count(*) as 'quantidade', date(data_doacao) as 'dia' FROM doacao where data_doacao between diaInicial and diaFinal and campanha_id_campanha = campanha group by date(data_doacao);
END
"""

mycursor.execute(sqlExecutar)

sqlExecutar="""
CREATE PROCEDURE proc_doacoes_valor(in mes int, in campanha int)
BEGIN
	declare diaInicial, diaFinal varchar(8);
    if mes=1 then
		set diaInicial="20220101";
        set diaFinal="20220131";
	elseif mes=2 then
		set diaInicial="20220201";
        set diaFinal="20220228";
	elseif mes=3 then
		set diaInicial="20220301";
        set diaFinal="20220331";
	elseif mes=4 then
		set diaInicial="20220401";
        set diaFinal="20220430";
	elseif mes=5 then
		set diaInicial="20220501";
        set diaFinal="20220531";
	elseif mes=6 then
		set diaInicial="20220601";
        set diaFinal="20220630";
    elseif mes=7 then
		set diaInicial="20220701";
        set diaFinal="20220731";
	elseif mes=8 then
		set diaInicial="20220801";
        set diaFinal="20220831";
	elseif mes=9 then
		set diaInicial="20220901";
        set diaFinal="20220930";
	elseif mes=10 then
		set diaInicial="20221001";
        set diaFinal="20221031";
	elseif mes=11 then
		set diaInicial="20221101";
        set diaFinal="20221130";
	elseif mes=12 then
		set diaInicial="20221201";
        set diaFinal="20221231";
	end if;
		select
sum(case when valor_doacao <=10 then 1 else 0 end) AS 'dez',
sum(case when valor_doacao between 10.05 and 20 then 1 else 0 end) AS 'vinte',
sum(case when valor_doacao between 20.05 and 30 then 1 else 0 end) AS 'trinta',
sum(case when valor_doacao between 30.05 and 40 then 1 else 0 end) AS 'quarenta',
sum(case when valor_doacao between 40.05 and 50 then 1 else 0 end) AS 'cinquenta',
sum(case when valor_doacao >50 then 1 else 0 end) AS 'outros_valores'
from doacao where data_doacao between diaInicial and diaFinal and campanha_id_campanha=campanha;
END
"""

mycursor.execute(sqlExecutar)

sqlExecutar="""
CREATE PROCEDURE proc_alterar_campanha_teste(in tipo int, in estado char(2))
BEGIN
	declare cepInicial,cepFinal int;
	if estado='SP' then
        set cepInicial=1000000;
        set cepFinal=19999999;
    elseif estado='RJ' then
        set cepInicial=20000000;
        set cepFinal=28999999;
    elseif estado='ES' then
        set cepInicial=29000000;
        set cepFinal=29999999;
    elseif estado='MG' then
        set cepInicial=30000000;
        set cepFinal=39999999;
    elseif estado='BA' then
        set cepInicial=40000000;
        set cepFinal=48999999;
    elseif estado='SE' then
        set cepInicial=49000000;
        set cepFinal=49999999;
    elseif estado='PE' then
        set cepInicial=50000000;
        set cepFinal=56999999;
    elseif estado='AL' then
        set cepInicial=57000000;
        set cepFinal=57999999;
    elseif estado='PB' then
        set cepInicial=58000000;
        set cepFinal=58999999;
    elseif estado='RN' then
        set cepInicial=59000000;
        set cepFinal=59999999;
    elseif estado='CE' then
        set cepInicial=60000000;
        set cepFinal=63999999;
    elseif estado='PI' then
        set cepInicial=64000000;
        set cepFinal=64999999;
    elseif estado='MA' then
        set cepInicial=65000000;
        set cepFinal=65999999;
    elseif estado='PA' then
        set cepInicial=66000000;
        set cepFinal=68899999;
    elseif estado='AP' then
        set cepInicial=68900000;
        set cepFinal=68999999;
    elseif estado='AM' then
        set cepInicial=69400000;
        set cepFinal=69899999;
    elseif estado='RR' then
        set cepInicial=69300000;
        set cepFinal=69389999;
    elseif estado='AC' then
        set cepInicial=69900000;
        set cepFinal=69999999;
    elseif estado='DF' then
        set cepInicial=70000000;
        set cepFinal=73699999;
    elseif estado='GO' then
        set cepInicial=72800000;
        set cepFinal=76799999;
    elseif estado='TO' then
        set cepInicial=77000000;
        set cepFinal=77995999;
    elseif estado='MT' then
        set cepInicial=78000000;
        set cepFinal=78899999;
    elseif estado='RO' then
        set cepInicial=78900000;
        set cepFinal=78999999;
    elseif estado='MS' then
        set cepInicial=79000000;
        set cepFinal=79999999;
    elseif estado='PR' then
        set cepInicial=80000000;
        set cepFinal=87999999;
    elseif estado='SC' then
        set cepInicial=88000000;
        set cepFinal=89999999;
    elseif estado='RS' then
        set cepInicial=90000000;
        set cepFinal=99999999;
	END IF;
	if tipo=4 or tipo=0 then
		update campanha set recomendado=tipo where ong_cod in (select cod from ong where cep between cepInicial and cepFinal);
        select count(*) from campanha where ong_cod in (select cod from ong where cep between cepInicial and cepFinal);
	else
		update campanha set recomendado=tipo where ong_cod in (select cod from ong where cep between cepInicial and cepFinal) and tipo_campanha=tipo;
        select count(*) from campanha where ong_cod in (select cod from ong where cep between cepInicial and cepFinal) and tipo_campanha=tipo;
	end if;
END
"""

mycursor.execute(sqlExecutar)
mydb.commit()
osArquivo = open('inserir.txt','r')
osDados = osArquivo.readlines()
for osDado in osDados:
    osDado=osDado.replace("\n","")
    mycursor.execute(osDado)
mydb.commit()