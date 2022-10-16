package betterplace.betterplacebcd.services.dashboard;
import betterplace.betterplacebcd.entidade.Doacao;

import java.time.LocalDate;
import java.util.List;

public interface IDashboardService {
    Integer getMaiorCampanha(String diaInicio, String diaFinal, Integer idOng);
    List<Object> getDoacoesDia(String diaInicio, String diaFinal, Integer idCampanha);
    List<Object> getDoacoesMes(String d1, String d2, String d3, String d4, String d5, String d6, String d7, String d8, String d9, String d10, Integer idCampanha);
    List<Object> getDoacoesValor(Integer idCampanha);
    List<Object> getProcedure(String estado);
}
