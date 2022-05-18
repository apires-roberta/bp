package bp.logincadastrobcd.repositorio;

import bp.logincadastrobcd.entidade.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DoadorRepository extends JpaRepository<Doador, Integer> {
    List<Doador> findByCod(Integer idDoador);
    List<Doador> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("update Doador d set d.autenticado = ?2 where d.email =?1")
    void Logar(String email, boolean autenticado);
}
