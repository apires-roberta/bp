package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.FilaObj;
import betterplace.betterplacebcd.data.dto.inscricao.ReadInscricaoDto;
import betterplace.betterplacebcd.entidade.Inscricao;
import betterplace.betterplacebcd.repositorio.DoadorRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
import betterplace.betterplacebcd.repositorio.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/inscricao")
@CrossOrigin
public class InscricaoController {
    @Autowired
    private InscricaoRepository inscricaoRepository;
    @Autowired
    private OngRepository ongRepository;
    @Autowired
    private DoadorRepository doadorRepository;

    private FilaObj<Inscricao> filainscritos;

    @GetMapping("/{idOng}")
    public ResponseEntity<List<ReadInscricaoDto>> getinscritosOng (@PathVariable Integer idOng){
        if (!inscricaoRepository.existsByFkOng(idOng))
            return status(204).build();

        List<ReadInscricaoDto> inscritos = inscricaoRepository.findByFkOng(idOng);
        return status(200).body(inscritos);
    }
    @PostMapping()
    public ResponseEntity createSeguidor (@RequestBody Inscricao inscricao){
        if (inscricaoRepository.existsByFkOngAndFkDoador(inscricao.fkOng, inscricao.fkDoador))
            return status(409).build();

        inscricaoRepository.save(inscricao);
        return status(201).build();
    }
}