package com.bp.feedbcd.repository;

import com.bp.feedbcd.entidade.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findAllByOrderByDataPublicacaoDesc();
    Feed findByCodigo(long codigo);
    List<Feed> findByOngCod(Integer idOng);
    List<Feed> findAllByOngCodInOrderByDataPublicacaoDesc(List<Integer> idsOng);
}