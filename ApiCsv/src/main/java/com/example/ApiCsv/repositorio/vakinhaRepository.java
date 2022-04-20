package com.example.ApiCsv.repositorio;

import com.example.ApiCsv.entidade.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface vakinhaRepository
        extends JpaRepository<Vakinha, Integer> {
/*
    1º findByFkOng (nome, descricao, valor necessario, dia que foi criada) 4
    2º countByFkVakinha (Quantidade de doacoes) 1
    3° findByDataDoacaoBetweenAndFkVakinhaOrderByDataDoacaoDesc  (idDoador, ultimo valor doado, valores doados, ultimo tempo doado) 2
    4º findByIdDoador (nome doador) 1
    5º valorAtualVakinha (valor atual) 1
    6º valorPorData (valores por cada dia)
    7º forecast
*/
    List<Vakinha> findByFkOng(Integer idOng);
    //@Query("select sum(valor_doacao), date(data_doacao) from doacao where fk_vakinha= ?1")
    //List<dataValor> valorPorData(Integer idVakinha);
}
