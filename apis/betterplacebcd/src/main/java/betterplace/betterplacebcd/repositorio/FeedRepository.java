package betterplace.betterplacebcd.repositorio;

//import api.feed.apifeed.entidade.Feed;
import betterplace.betterplacebcd.entidade.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findAllByOrderByDataPublicacaoDesc();

    Feed findByCodigo(long codigo);


}
