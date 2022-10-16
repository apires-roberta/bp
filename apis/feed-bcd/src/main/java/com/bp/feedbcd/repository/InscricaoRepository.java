package com.bp.feedbcd.repository;

import com.bp.feedbcd.entidade.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//InscricaoId
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
    List<Inscricao> findByOngCod(Integer idOng);
    boolean existsByOngCodAndDoadorCod(Integer fkOng, Integer fkDoador);
    @Query("SELECT insc.ong.cod from Inscricao insc where insc.doador.cod = ?1")
    List<Integer> findByDoadorCod(Integer idDoador);
    Inscricao findByOngCodAndDoadorCod(Integer fkOng, Integer fkDoador);
}