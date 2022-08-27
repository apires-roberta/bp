package com.bp.feedbcd.repository;

import com.bp.feedbcd.entidade.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//InscricaoId
public interface InscricaoRepository extends JpaRepository<Inscricao,Integer> {
List<Inscricao> findByOngCod(Integer idOng);

boolean existsByOngCod(Integer idOng);

boolean existsByOngCodAndDoadorCod(Integer fkOng, Integer fkDoador);

//<S extends Inscricao> S save(InscricaoId entity);
}