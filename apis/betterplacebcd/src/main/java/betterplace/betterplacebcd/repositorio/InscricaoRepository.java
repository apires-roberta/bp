package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.data.dto.inscricao.ReadInscricaoDto;
import betterplace.betterplacebcd.entidade.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {
    List<ReadInscricaoDto> findByFkOng(Integer idOng);

    boolean existsByFkOng(Integer idOng);

    boolean existsByFkOngAndFkDoador(Integer fkOng, Integer fkDoador);
}