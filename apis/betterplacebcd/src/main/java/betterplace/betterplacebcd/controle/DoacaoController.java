package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.Notificacao;
import betterplace.betterplacebcd.data.dto.doacao.CreateDoacaoDto;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import betterplace.betterplacebcd.services.doacao.IDoacaoService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.status;

@Service
@RestController
@RequestMapping("/doacao")
public class DoacaoController {
    @Autowired
    private DoacoesRepository doacoesRepository;
    @Autowired
    private IDoacaoService _doacaoService;
    @PostMapping("")
    public ResponseEntity doar(@RequestBody @Valid CreateDoacaoDto doacaoDto){
        if (doacaoDto == null)
            return status(400).build();

        try {
            _doacaoService.doar(doacaoDto);
            return status(201).build();
        }catch (FeignException.NotFound ex){
            return status(404).build();
        }
    }
}