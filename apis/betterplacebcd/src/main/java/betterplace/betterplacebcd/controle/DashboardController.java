package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.ed.Dashboard;
import betterplace.betterplacebcd.data.dto.campanha.ReadCampanhaDto;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.services.dashboard.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@Service
@RestController
@CrossOrigin
@RequestMapping("/dash")
public class DashboardController {
    @Autowired
    private DashboardService _dashboardService;
    @GetMapping("/{cod}/{diaInicio}/{diaFinal}")
    public ResponseEntity<?> getMaiorCampanha(@PathVariable String diaInicio, @PathVariable String diaFinal, @PathVariable Integer cod) {
        Integer idCampanha = _dashboardService.getMaiorCampanha(diaInicio, diaFinal, cod);
        if (idCampanha == null)
            return status(204).build();
        List<Object> doacoes = _dashboardService.getDoacoesDia(diaInicio, diaFinal, idCampanha);
        Object semana = _dashboardService.getDoacoesMes(diaInicio.substring(0,6)+"01",
                diaInicio.substring(0,6)+"07", diaInicio.substring(0,6)+"08",diaInicio.substring(0,6)+"14",
                diaInicio.substring(0,6)+"15", diaInicio.substring(0,6)+"21",diaInicio.substring(0,6)+"22",
                diaInicio.substring(0,6)+"28", diaInicio.substring(0,6)+"29",diaInicio.substring(0,6)+"31",
                idCampanha);
        Object valor = _dashboardService.getDoacoesValor(idCampanha);

        Dashboard dashboard = new Dashboard(idCampanha, doacoes, semana, valor);

        return status(200).body(dashboard);
    }

    @GetMapping("/{estado}")
    public ResponseEntity<?> getEstado(@PathVariable String estado) {
        List<Object> dadosEstado = _dashboardService.getProcedure(estado);
        if (dadosEstado == null)
            return status(204).build();
        return status(200).body(dadosEstado);
    }
}
