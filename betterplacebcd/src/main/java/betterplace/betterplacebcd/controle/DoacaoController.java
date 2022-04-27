package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.Notificacao;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import betterplace.betterplacebcd.repositorio.DoadorRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {
    @Autowired
    private DoacoesRepository dcr;
    @Autowired
    private DoadorRepository dor;
    @Autowired
    private OngRepository or;
    @Autowired
    private CampanhaRepository vr;
    @PostMapping("")
    public ResponseEntity doar(@RequestBody @Valid Doacao doacao){
        Integer idOng = doacao.getFkOng();
        Integer idDoador = doacao.getFkDoador();
        Integer idCampanha = doacao.getFkCampanha();
        doacao.setDataDoacao(LocalDateTime.now());
        dcr.save(doacao);
        String mensagem = String.format("A(O) %s mandou R$%.2f para campanha: %s", dor.findByCod(idDoador).get(0).getNome(),
                doacao.getValorDoacao(), vr.findByIdCampanha(idCampanha).get(0).getNomeCampanha());
        String email = or.findByCod(idOng).get(0).getEmail();
        Notificacao revistaInformatica = new Notificacao();
        revistaInformatica.novaDoacao(email, mensagem);
        return ResponseEntity.status(201).build();
    }
}
