package bp.logincadastrobcd.repositorio;

import bp.logincadastrobcd.entidade.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
public interface DoadorRepository extends JpaRepository<Doador, Integer> {
    Optional<Doador> findByCod(Integer idUsuario);
    boolean existsByCodAndAutenticadoTrue(Integer idUsuario);
    Doador findByEmail(String email);
    boolean existsByEmailAndSenha(String email, String senha);
    boolean existsByEmail(String email);

    List<Doador> findByNomeContains(@Param("nomeDoador") String nomeDoador);
}