package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.entidade.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OngRepository extends JpaRepository<Ong, Integer> {
    List<Ong> findByCod(Integer idOng);
    List<Ong> findByEmail(String email);
    String findNomeByCod(Integer idOng);
    @Transactional
    @Modifying
    @Query("update Ong o set o.autenticado = ?2 where o.email =?1")
    void Logar(String email, boolean autenticado);
}
