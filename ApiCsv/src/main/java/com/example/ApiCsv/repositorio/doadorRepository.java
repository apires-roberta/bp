package com.example.ApiCsv.repositorio;

import com.example.ApiCsv.entidade.Doador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface doadorRepository extends JpaRepository<Doador, Integer> {
    List<Doador> findByIdDoador(Integer idDoador);
}
