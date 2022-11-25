package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.data.dto.campanha.ReadCampanhaDto;
import betterplace.betterplacebcd.entidade.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {
    List<Campanha> findByOngCodOrderByDataCriacaoDesc(Integer idOng);
    Campanha findByIdCampanha(Integer idCampanha);
    Integer countByOngCod(Integer idOng);
    List<Campanha> findByDisponivelTrueOrderByDataCriacaoDesc();
    List<Campanha> findByDisponivelTrueAndIdCampanhaIsNot(Integer idCampanha);
    List<Campanha> findByOngCodAndDisponivelTrueOrderByDataCriacaoDesc(Integer idOng);
    Integer countByOngCodAndDisponivelTrue(Integer idOng);
    @Query(value = "select c.id_campanha from campanha as c where c.ong_cod=?3 order by (select sum(d.valor_doacao) from doacao as d where d.campanha_id_campanha=c.id_campanha and d.data_doacao between ?1 and ?2) desc limit 0,1;", nativeQuery=true)
    Integer getIdCampanha(String diaInicio, String diaFinal, Integer idOng);
    //GetTipoCampanhaDto findTipoCampanhaByIdCampanha(Integer idCampanha);
    @Query("select c.tipoCampanha from Campanha c where c.idCampanha = ?1")
    Integer findTipoCampanhaByIdCampanha(Integer idCampanha);
    @Query("SELECT camp from Campanha camp where camp.idCampanha in (select distinct d.campanha.idCampanha from Doacao as d where d.doador.cod in (select d.doador.cod from Doacao as d where d.campanha.idCampanha = ?1 and d.doador.cod <> ?2) and d.campanha.idCampanha <> ?1)")
    List<Campanha> getRecomendacoesByDoacoes(int idCampanha, Integer idDoador);
    @Query(value="call proc_alterar_campanha_teste(?1,?2)", nativeQuery=true)
    Integer alterarDados(int tipo, String estado);
}