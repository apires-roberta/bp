package repositorio;


import entidade.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoacoesRepository extends JpaRepository<Doacao, Integer> {
    Integer countByFkCampanha(Integer idCampanha);
    List<Doacao> findByFkCampanhaOrderByDataDoacaoDesc(Integer idCampanha);
}
