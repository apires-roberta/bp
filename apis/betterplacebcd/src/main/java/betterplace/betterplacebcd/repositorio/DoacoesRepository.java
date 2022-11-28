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
    @Query(value="call proc_doacoes_dia(?1,?2)", nativeQuery = true)
    List<Object> getDoacoesDia(Integer mes, Integer idCampanha);
    @Query(value="call proc_doacoes_semana(?1,?2)", nativeQuery = true)
    List<Object> getDoacoesMes(Integer mes, Integer idCampanha);

    @Query(value="call proc_doacoes_valor(?1,?2)", nativeQuery = true)
    List<Object> getDoacoesValor(Integer mes, Integer idCampanha);
    @Query(value="call proc_estado(?1)", nativeQuery = true)
    List<Object> getProcedure(String estado);
    Integer countByDoadorCod(Integer idDoador);
    List<Doacao> findByDoadorCod(Integer idDoador);
}