package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.FilaObj;
import betterplace.betterplacebcd.data.dto.inscricao.CreateInscricaoDto;
import betterplace.betterplacebcd.data.dto.inscricao.InscricaoId;
import betterplace.betterplacebcd.data.dto.inscricao.ReadInscricaoDto;
import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.entidade.Inscricao;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.DoadorRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
import betterplace.betterplacebcd.repositorio.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<ReadInscricaoDto>> getInscritosOng (@PathVariable Integer idOng){
        if (!inscricaoRepository.existsByOngCod(idOng))
            return status(204).build();

        List<Inscricao> inscritos = inscricaoRepository.findByOngCod(idOng);
        List<ReadInscricaoDto> inscritosDto = new ArrayList<>();
        for (Inscricao inscrito : inscritos) {
            ReadInscricaoDto inscricaoDto = new ReadInscricaoDto();
        }
        return status(200).body(inscritosDto);
    }
    @PostMapping()
    public ResponseEntity createInscricao (@RequestBody CreateInscricaoDto novaInscricao){
        if (inscricaoRepository.existsByOngCodAndDoadorCod(novaInscricao.getFkOng(), novaInscricao.getFkDoador()))
            return status(409).build();

        Optional<Ong> ong = ongRepository.findByCod(novaInscricao.getFkOng());
        Optional<Doador> doador = doadorRepository.findByCod(novaInscricao.getFkDoador());
        if (ong.isEmpty() || doador.isEmpty())
            return status(404).build();

        Inscricao inscricao = new Inscricao(ong.get(), doador.get());
        inscricaoRepository.save(inscricao);
        return status(201).build();
    }
}