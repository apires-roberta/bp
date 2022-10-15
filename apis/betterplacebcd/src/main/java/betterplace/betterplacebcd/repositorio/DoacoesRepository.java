package betterplace.betterplacebcd.repositorio;

import betterplace.betterplacebcd.entidade.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoacoesRepository extends JpaRepository<Doacao, Integer> {
    Integer countByCampanhaIdCampanha(Integer idCampanha);
    List<Doacao> findByCampanhaIdCampanhaOrderByDataDoacaoDesc(Integer idCampanha);
    @Query("SELECT coalesce(sum(d.valorDoacao), 0) from Doacao d where d.campanha.idCampanha = ?1") //Igual o NVL do Oracle -> Se a soma dos valores doados der null, ele retorna 0
    double sumValorDoadoCampanha(Integer idCampanha);
    Doacao findByIdDoacao(Integer idDoacao);
    Doacao findFirstByDoadorCodAndCampanhaIdCampanhaOrderByDataDoacaoDesc(Integer idDoador, Integer idCampanha);
    @Query("SELECT coalesce(sum(d.valorDoacao), 0) from Doacao d where d.campanha.idCampanha in " +
                "(select camp.idCampanha from Campanha camp where camp.ong.cod = ?1)")
    double sumValorDoadoOng(Integer idOng);

    @Query(value="SELECT count(*) as 'quantidade', date(data_doacao) as 'dia' FROM doacao where data_doacao between ?1 and ?2 and campanha_id_campanha = ?3 group by date(data_doacao);", nativeQuery = true)
    List<Object> getDoacoesDia(String diaInicio, String diaFinal, Integer idCampanha);

    @Query(value="select" +
            " sum(case when data_doacao between ?1 and ?2 then 1 else 0 end) AS 's_um'," +
            " sum(case when data_doacao between ?3 and ?4 then 1 else 0 end) AS 's_dois'," +
            " sum(case when data_doacao between ?5 and ?6 then 1 else 0 end) AS 's_tres'," +
            " sum(case when data_doacao between ?7 and ?8 then 1 else 0 end) AS 's_quatro'," +
            " sum(case when data_doacao between ?9 and ?10 then 1 else 0 end) AS 's_cinco'" +
            " from doacao where campanha_id_campanha=?11", nativeQuery = true)
    List<Object> getDoacoesMes(String d1, String d2, String d3, String d4, String d5, String d6, String d7, String d8, String d9, String d10, Integer idCampanha);

    @Query(value="select" +
            " coalesce(sum(case when valor_doacao <=10 then 1 else 0 end),0) AS 'dez'," +
            " coalesce(sum(case when valor_doacao <=20 then 1 else 0 end),0) AS 'vinte'," +
            " coalesce(sum(case when valor_doacao <=30 then 1 else 0 end),0) AS 'trinta'," +
            " coalesce(sum(case when valor_doacao <=40 then 1 else 0 end),0) AS 'quarenta'," +
            " coalesce(sum(case when valor_doacao <=50 then 1 else 0 end),0) AS 'cinquenta'," +
            " coalesce(sum(case when valor_doacao >50 then 1 else 0 end),0) AS 'outros_valores'" +
            " from doacao where campanha_id_campanha=?1", nativeQuery = true)
    List<Object> getDoacoesValor(Integer idCampanha);

    @Query(value="call proc_estado(?1)", nativeQuery = true)
    List<Object> getProcedure(String estado);
}