package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.entidade.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {
    List<Campanha> findByOngCod(Integer idOng);
    Campanha findByIdCampanha(Integer idCampanha);
    Integer countByOngCod(Integer idOng);
    List<Campanha> findByDisponivelTrue();
    List<Campanha> findByOngCodAndDisponivelTrue(Integer idOng);

    Integer countByOngCodAndDisponivelTrue(Integer idOng);
    @Query(value = "select c.id_campanha from campanha as c where c.ong_cod=?3 order by (select sum(d.valor_doacao) from doacao as d where d.campanha_id_campanha=c.id_campanha and d.data_doacao between ?1 and ?2) desc limit 0,1;", nativeQuery=true)
    Integer getIdCampanha(String diaInicio, String diaFinal, Integer idOng);
}