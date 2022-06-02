package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.entidade.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DoacoesRepository extends JpaRepository<Doacao, Integer> {
    Integer countByCampanhaIdCampanha(Integer idCampanha);
    List<Doacao> findByCampanhaIdCampanhaOrderByDataDoacaoDesc(Integer idCampanha);
}
