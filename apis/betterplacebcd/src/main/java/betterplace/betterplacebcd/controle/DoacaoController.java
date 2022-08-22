package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.Notificacao;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/doacao")
public class DoacaoController {
    @Autowired
    private DoacoesRepository doacoesRepository;
    @PostMapping("")
    public ResponseEntity doar(@RequestBody @Valid Doacao doacao){
        doacao.setDataDoacao(LocalDateTime.now());
        if (doacao.getDoador() == null || doacao.getCampanha() == null || doacao.getValorDoacao() == null || doacao.getOng() == null || doacao.getIdDoacao() == null)
            return ResponseEntity.status(400).build();
        doacoesRepository.save(doacao);
        String mensagem = String.format("A(O) %s mandou R$%.2f para campanha: %s", doacao.getDoador().getNome(),
                doacao.getValorDoacao(), doacao.getCampanha().getNomeCampanha());
        String email = doacao.getOng().getEmail();
        Notificacao novaNotificacao = new Notificacao();
        novaNotificacao.novaDoacao(email, mensagem);
        return ResponseEntity.status(201).build();
    }
}