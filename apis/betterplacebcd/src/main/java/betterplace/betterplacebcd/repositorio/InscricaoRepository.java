package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.data.dto.inscricao.InscricaoId;
import betterplace.betterplacebcd.data.dto.inscricao.ReadInscricaoDto;
import betterplace.betterplacebcd.entidade.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscricaoRepository extends JpaRepository<Inscricao, InscricaoId> {
    List<ReadInscricaoDto> findByOngCod(Integer idOng);

    boolean existsByOngCod(Integer idOng);

    boolean existsByOngCodAndDoadorCod(Integer fkOng, Integer fkDoador);

    <InscricaoId extends betterplace.betterplacebcd.data.dto.inscricao.InscricaoId> betterplace.betterplacebcd.data.dto.inscricao.InscricaoId save(betterplace.betterplacebcd.data.dto.inscricao.InscricaoId entity);
}