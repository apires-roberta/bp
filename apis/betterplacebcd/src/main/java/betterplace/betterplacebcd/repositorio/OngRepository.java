package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.entidade.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OngRepository extends JpaRepository<Ong, Integer> {
    Optional<Ong> findByCod(Integer idOng);
    Optional<Ong> findByEmail(String email);
//    String findNomeByCod(Integer idOng);
//    List<Object> findDoadoresByCod(Integer idOng);
    @Transactional
    @Modifying
    @Query("update Ong o set o.autenticado = ?2 where o.email =?1")
    void Logar(String email, boolean autenticado);
}
