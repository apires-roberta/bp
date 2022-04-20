package com.example.ApiCsv.repositorio;

import com.example.ApiCsv.entidade.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface doacoesRepository extends JpaRepository<Doacao, Integer> {
    Long countByFkVakinha(Integer idVakinha);
    List<Doacao> findByFkVakinhaOrderByDataDoacaoDesc(Integer idVakinha);
    List<Doacao> findByDataDoacaoBetweenAndFkVakinha(LocalDateTime diaInicio, LocalDateTime diaFim, Integer idVakinha);
}
