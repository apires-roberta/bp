package controle;

import entidade.Doacao;
import repositorio.DoacoesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {
    @Autowired
    private DoacoesRepository dcr;
    @Autowired
    private DoadorRepository dor; // Requer conexão com as outras APIS
    @Autowired
    private OngRepository or; // Requer conexão com as outras APIS
    @Autowired
    private CampanhaRepository vr; // Requer conexão com as outras APIS
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
