package com.bp.feedbcd.repository;

import com.bp.feedbcd.entidade.NotificacaoFeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoFeedRepository extends JpaRepository<NotificacaoFeed, Integer> {
    List<NotificacaoFeed> findTop10ByInscricaoDoadorCodOrderByDataNotificacao(Integer idDoador);
}