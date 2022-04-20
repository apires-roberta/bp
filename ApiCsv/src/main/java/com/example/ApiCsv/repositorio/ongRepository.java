package com.example.ApiCsv.repositorio;

import com.example.ApiCsv.entidade.ong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ongRepository extends JpaRepository<ong, Integer> {
    List<ong> findByIdOng(Integer idOng);
}
