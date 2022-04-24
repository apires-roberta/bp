package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.entidade.Ong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OngRepository extends JpaRepository<Ong, Integer> {
    List<Ong> findByIdOng(Integer idOng);
}
