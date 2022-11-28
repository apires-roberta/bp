package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.data.dto.doacao.CreateDoacaoDto;
import betterplace.betterplacebcd.data.dto.doacao.ReadDoacaoDto;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import betterplace.betterplacebcd.services.doacao.IDoacaoService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@Service
@CrossOrigin
@RestController
@RequestMapping("/doacao")
public class DoacaoController {
    @Autowired
    private IDoacaoService _doacaoService;
    @PostMapping("")
    public ResponseEntity<?> doar(@RequestBody @Valid CreateDoacaoDto doacaoDto){
        if (doacaoDto == null)
            return status(400).build();

        try {
           Integer idDoacao =  _doacaoService.doar(doacaoDto);
            return status(201).body(idDoacao);
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }catch (FeignException.ServiceUnavailable ex){
            return status(503).build();
        }
    }

    @GetMapping("/campanha/{idCampanha}")
    public ResponseEntity<List<ReadDoacaoDto>> getDoacoesByIdCampanha(@PathVariable Integer idCampanha){
        if (idCampanha == null || idCampanha <= 0)
            return status(400).build();

        try {
            List<ReadDoacaoDto> doacoes = _doacaoService.getDoacoesByIdCampanha(idCampanha);
            return doacoes.isEmpty() ? status(204).build() : status(200).body(doacoes);
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }
    }

    @GetMapping("/{idDoacao}")
    public ResponseEntity<ReadDoacaoDto> getDoacaoById(@PathVariable Integer idDoacao) {
        if (idDoacao == null || idDoacao <= 0)
            return status(400).build();

        try {
            ReadDoacaoDto doacao = _doacaoService.getDoacaoByIdDoacao(idDoacao);

            return doacao == null ? status(404).build() : status(200).body(doacao);
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/doador/{idDoador}/campanha/{idCampanha}")
    public ResponseEntity<ReadDoacaoDto> getUltimaDoacaoDoadorCampanha(@PathVariable Integer idDoador, @PathVariable Integer idCampanha){
        if (idDoador <= 0 || idCampanha <= 0 || idDoador == null || idCampanha == null)
            return status(400).build();
        try {
            ReadDoacaoDto doacao = _doacaoService.getUltimaDoacaoDoadorCampanha(idDoador, idCampanha);
            return doacao == null ? status(404).build() : status(200).body(doacao);
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("total/ong/{idOng}")
    public ResponseEntity<Double> getTotalRecebidoOng(@PathVariable Integer idOng){
        if (idOng <= 0 || idOng == null)
            return status(400).build();

        try {
            Double valor = _doacaoService.getTotalRecebidoOng(idOng);
            return valor == null ? status(404).build() : status(200).body(valor);
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }catch (Exception ex){
            throw ex;
        }
    }
    
    @GetMapping("quantidade/doador/{idDoador}")
    public ResponseEntity<Integer> getQtdDoacoesDoador(@PathVariable Integer idDoador){
        if (idDoador <= 0 || idDoador == null)
            return status(400).build();

        try {
            Integer qtdDoacoes = _doacaoService.getQtdDoacoesDoador(idDoador);

            return qtdDoacoes == null ? status(404).build() : status(200).body(qtdDoacoes);
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }catch (Exception ex){
            throw ex;
        }
    }
    @GetMapping("doador/{idDoador}")
    public ResponseEntity<List<ReadDoacaoDto>> getDoacoesByIdDoador(@PathVariable Integer idDoador){
        if (idDoador <= 0 || idDoador == null)
            return status(400).build();

        try {
            List<ReadDoacaoDto> doacoes = _doacaoService.getDoacoesByIdDoador(idDoador);

            return doacoes == null ? status(404).build() : status(200).body(doacoes);
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}