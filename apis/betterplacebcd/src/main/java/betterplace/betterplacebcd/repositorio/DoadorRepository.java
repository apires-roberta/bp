package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.entidade.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DoadorRepository extends JpaRepository<Doador, Integer> {
    Optional<Doador> findByCod(Integer idDoador);
    List<Doador> findByEmail(String email);
    String findEmailByCod(Integer idDoador);
    boolean findByExist(Integer idDoador);
    @Transactional
    @Modifying
    @Query("update Doador d set d.autenticado = ?2 where d.email =?1")
    void Logar(String email, boolean autenticado);
}
