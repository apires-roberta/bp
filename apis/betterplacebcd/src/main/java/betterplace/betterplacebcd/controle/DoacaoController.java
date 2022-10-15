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
}