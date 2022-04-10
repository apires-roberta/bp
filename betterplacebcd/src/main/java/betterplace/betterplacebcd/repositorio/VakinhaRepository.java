package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.entidade.Vakinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VakinhaRepository extends JpaRepository<Vakinha, Integer> {

}
