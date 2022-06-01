package controle;

import entidade.Doacao;
import org.springframework.web.bind.annotation.*;
import repositorio.DoacoesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("/doacao")
public class DoacaoController {
    @Autowired
    private DoacoesRepository doacoesRepository;
    @Autowired
    private DoadorRepository doadorRepository; // Requer conexão com as outras APIS
    @Autowired
    private OngRepository ongRepository; // Requer conexão com as outras APIS
    @Autowired
    private CampanhaRepository campanhaRepository; // Requer conexão com as outras APIS
    @PostMapping("")
    public ResponseEntity doar(@RequestBody @Valid Doacao doacao){
        Integer idOng = doacao.getFkOng();
        Integer idDoador = doacao.getFkDoador();
        Integer idCampanha = doacao.getFkCampanha();
        doacao.setDataDoacao(LocalDateTime.now()); doacoesRepository.save(doacao);
        String mensagem = String.format("A(O) %s mandou R$%.2f para campanha: %s", doadorRepository.findByCod(idDoador).get(0).getNome(),
                doacao.getValorDoacao(), campanhaRepository.findByIdCampanha(idCampanha).get(0).getNomeCampanha());
        String email = ongRepository.findByCod(idOng).get(0).getEmail();
        Notificacao revistaInformatica = new Notificacao();
        revistaInformatica.novaDoacao(email, mensagem);
        return ResponseEntity.status(201).build();
    }
}
