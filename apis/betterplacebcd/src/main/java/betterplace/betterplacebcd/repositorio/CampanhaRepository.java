package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.entidade.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {
    List<Campanha> findByOngCod(Integer idOng);
    Campanha findByIdCampanha(Integer idCampanha);
    Integer countByOngCod(Integer idOng);
    List<Campanha> findByDisponivelTrue();
    List<Campanha> findByOngCodAndDisponivelTrue(Integer idOng);

    Integer countByOngCodAndDisponivelTrue(Integer idOng);
}