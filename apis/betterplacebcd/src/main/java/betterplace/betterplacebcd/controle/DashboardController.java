package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.ed.Dashboard;
import betterplace.betterplacebcd.data.dto.campanha.ReadCampanhaDto;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.services.dashboard.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@Service
@RestController
@CrossOrigin
@RequestMapping("/dash")
public class DashboardController {
    @Autowired
    private DashboardService _dashboardService;
    @GetMapping("/{cod}/{dataCriacao}")
    public ResponseEntity<?> getMaiorCampanha(@PathVariable Integer cod, @PathVariable String dataCriacao) {
        Integer mes = Integer.parseInt(dataCriacao.substring(5,7));
        List<Dashboard> dashboards = new ArrayList();
        Date data = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        Format format = new SimpleDateFormat("MM");
        while (Integer.parseInt(format.format(c.getTime()))>=mes){
            Integer idCampanha = _dashboardService.getMaiorCampanha("2022"+mes+"01", "2022"+mes+"31", cod);
            List<Object> doacoes = _dashboardService.getDoacoesDia(mes, idCampanha);
            Object semana = _dashboardService.getDoacoesMes(mes, idCampanha);
            Object valor = _dashboardService.getDoacoesValor(mes, idCampanha);
            Dashboard dashboard = new Dashboard(idCampanha, doacoes, semana, valor, mes);
            dashboards.add(dashboard);
            mes++;
        }
        return status(200).body(dashboards);
    }

    @GetMapping("/{estado}")
    public ResponseEntity<?> getEstado(@PathVariable String estado) {
        List<Object> dadosEstado = _dashboardService.getProcedure(estado);
        if (dadosEstado == null)
            return status(204).build();
        return status(200).body(dadosEstado);
    }
}
