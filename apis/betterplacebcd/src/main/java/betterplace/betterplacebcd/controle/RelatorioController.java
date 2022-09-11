package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.*;
import betterplace.betterplacebcd.data.dto.usuario.ReadUsuarioDto;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.repositorio.*;
import betterplace.betterplacebcd.services.relatorio.IRelatorioService;
import betterplace.betterplacebcd.servicesreferences.IDoadorService;
import betterplace.betterplacebcd.servicesreferences.IOngService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.springframework.http.ResponseEntity.*;

@Service
@CrossOrigin
@RestController
@RequestMapping("/relatorio")
public class RelatorioController {
    @Autowired
    private CampanhaRepository campanhaRepository;
    @Autowired
    private DoacoesRepository doacoesRepository;
    @Autowired
    private IRelatorioService _relatorioService;
    @Autowired
    private IDoadorService _doadorService;
    @Autowired
    private IOngService _ongService;
    private Forecast previsao = new Forecast();
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private GravarArquivo gravarArquivo = new GravarArquivo();
    private ExportarDados exportarDados = new ExportarDados();

    @GetMapping("/csv/{idOng}")
    public ResponseEntity montarDados(@PathVariable Integer idOng){
        try {
            String[] infos = _relatorioService.montarDados(idOng);

            return infos == null ? status(204).build() : status(200).header("content-type", "text/csv")
                                                        .header("content-disposition", "filename=\""+infos[0]+".csv\"")
                                                        .body(infos[1]);
        }catch(FeignException.NotFound ex){
            return status(404).build();
        }catch (FeignException.ServiceUnavailable ex){
            return status(503).build();
        }
    }

    @GetMapping("/Exportacao/{idOng}")
    public ResponseEntity exportarDados(@PathVariable Integer idOng){
        try {
            String[] infos = _relatorioService.exportarDados(idOng);

            return infos == null ? status(204).build() : status(200).header("content-type", "text/csv")
                                                        .header("content-disposition", "filename=\"Relatorio_exportacao_"+infos[0]+".txt\"")
                                                        .body(infos[1]);
        }catch(FeignException.NotFound ex){
            return status(404).build();
        }catch (FeignException.ServiceUnavailable ex){
            return status(503).build();
        }
    }

    @PatchMapping(value = "/foto/{codigo}", consumes="image/jpeg")
    public ResponseEntity patchFoto(@PathVariable long codigo, @RequestBody byte[] novaFoto){
        if(codigo == 0 || novaFoto == null)
            return status(400).build();
        return status(200).body(novaFoto);
    }
}
