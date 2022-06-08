package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.data.dto.inscricao.InscricaoId;
import betterplace.betterplacebcd.data.dto.inscricao.ReadInscricaoDto;
import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.entidade.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
                                                                    //InscricaoId
public interface InscricaoRepository extends JpaRepository<Inscricao,Integer> {
    List<Inscricao> findByOngCod(Integer idOng);

    boolean existsByOngCod(Integer idOng);

    boolean existsByOngCodAndDoadorCod(Integer fkOng, Integer fkDoador);

    //<S extends Inscricao> S save(InscricaoId entity);
}