package bp.logincadastrobcd.repositorio;

import bp.logincadastrobcd.entidade.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;

public interface OngRepository extends JpaRepository<Ong, Integer> {
    Optional<Ong> findByCod(Integer idOng);
    Ong findByEmail(String email);
    boolean existsByEmailAndSenha(@Valid String email, @Valid String senha);
    boolean existsByCodAndAutenticadoTrue(Integer idUsuario);
    boolean existsByEmail(String email);

//    @Transactional
//    @Modifying
//    @Query("update Ong o set o.autenticado = ?2 where o.email =?1")
//    void Logar(String email, boolean autenticado);
}
