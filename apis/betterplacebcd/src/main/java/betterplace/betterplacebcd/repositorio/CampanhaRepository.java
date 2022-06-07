package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.entidade.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {
    List<Campanha> findByOngCod(Integer idOng);
    List<Campanha> findByIdCampanha(Integer idCampanha);
    Integer countByOngCod(Integer idOng);
    boolean findByCodigo(int codigo);
}
