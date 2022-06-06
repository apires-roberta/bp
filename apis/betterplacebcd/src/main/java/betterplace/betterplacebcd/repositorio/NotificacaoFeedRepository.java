package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.data.dto.notificacaofeed.ReadNotificacaoFeedDto;
import betterplace.betterplacebcd.entidade.NotificacaoFeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoFeedRepository extends JpaRepository<NotificacaoFeed, Integer> {
    List<ReadNotificacaoFeedDto> findTop10ByFkDoadorOrderByDataNotificacao(Integer idDoador);
}