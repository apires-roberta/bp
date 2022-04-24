package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.entidade.Doador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoadorRepository extends JpaRepository<Doador, Integer> {
    List<Doador> findByIdDoador(Integer idDoador);
}
