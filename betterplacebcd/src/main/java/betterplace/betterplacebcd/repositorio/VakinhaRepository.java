package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.entidade.Vakinha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VakinhaRepository extends JpaRepository<Vakinha, Integer> {
    List<Vakinha> findByFkOng(Integer idOng);
    Integer countByFkOng(Integer idOng);
}
