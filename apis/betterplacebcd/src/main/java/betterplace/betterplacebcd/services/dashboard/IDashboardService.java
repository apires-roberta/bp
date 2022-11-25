package betterplace.betterplacebcd.services.dashboard;
import betterplace.betterplacebcd.entidade.Doacao;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IDashboardService {
    Integer getMaiorCampanha(String diaInicio, String diaFinal, Integer idOng);
    List<Object> getDoacoesDia(Integer mes, Integer idCampanha);
    List<Object> getDoacoesMes(Integer mes, Integer idCampanha);
    List<Object> getDoacoesValor(Integer mes, Integer idCampanha);
    List<Object> getProcedure(String estado);
    Integer alterarDados(int tipo, String estado);
}
