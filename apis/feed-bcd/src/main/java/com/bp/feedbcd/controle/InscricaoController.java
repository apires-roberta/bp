package com.bp.feedbcd.controle;

import com.bp.feedbcd.data.dto.inscricao.CreateInscricaoDto;
import com.bp.feedbcd.data.dto.usuario.ReadUsuarioDto;
import com.bp.feedbcd.services.inscricao.IInscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@Service
@RestController
@RequestMapping("/inscricao")
@CrossOrigin
public class InscricaoController {
    @Autowired
    private IInscricaoService _inscricaoService;

    @PostMapping()
    public ResponseEntity createInscricao(@RequestBody CreateInscricaoDto novaInscricao) {
        if (novaInscricao == null)
            return status(400).build();

        Boolean criado = _inscricaoService.createInscricao(novaInscricao);
        if (criado == null)
            return status(404).build();

        return criado ? status(201).build() : status(409).build();
    }

    @GetMapping("/ong/{idOng}")
    public ResponseEntity<List<ReadUsuarioDto>> getInscritosOng(@PathVariable Integer idOng) {
        if (idOng == null)
            return status(400).build();

        List<ReadUsuarioDto> inscritos = _inscricaoService.getInscritosOng(idOng);
        if (inscritos == null)
            return status(404).build();

        return inscritos.isEmpty() ? status(204).build() : status(200).body(inscritos);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteInscricao(@RequestBody CreateInscricaoDto delInscricao) {
        if (delInscricao == null)
            return status(400).build();

        try {
            _inscricaoService.deleteInscricao(delInscricao);

            return status(204).build();
        }catch (IllegalArgumentException ex){
            return status(404).build();
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/existe/doador/{idDoador}/ong/{idOng}")
    public ResponseEntity<?> existeInscricao(@PathVariable Integer idDoador, @PathVariable Integer idOng){
        CreateInscricaoDto existsInscricao = new CreateInscricaoDto(idOng, idDoador);
        try {
            _inscricaoService.existeInscricao(existsInscricao);
            return status(204).build();
        }catch (IllegalArgumentException ex){
            return status(404).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}