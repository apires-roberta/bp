package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.Email;
import betterplace.betterplacebcd.classes.FilaObj;
import betterplace.betterplacebcd.classes.PilhaObj;
import betterplace.betterplacebcd.data.dto.inscricao.ReadInscricaoDto;
import betterplace.betterplacebcd.data.dto.notificacaofeed.ReadNotificacaoFeedDto;
import betterplace.betterplacebcd.entidade.Inscricao;
import betterplace.betterplacebcd.entidade.NotificacaoFeed;
import betterplace.betterplacebcd.repositorio.DoadorRepository;
import betterplace.betterplacebcd.repositorio.NotificacaoFeedRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
import betterplace.betterplacebcd.repositorio.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/notificacaoFeed")
@CrossOrigin
public class NotificacaoFeedController {
    @Autowired
    private NotificacaoFeedRepository notificacaoRepository;
    @Autowired
    private InscricaoRepository inscricaoRepository;
    @Autowired
    private OngRepository ongRepository;
    @Autowired
    private DoadorRepository doadorRepository;

    private FilaObj<Inscricao> filaInscritos;
    PilhaObj<ReadNotificacaoFeedDto> pilhaNotificacoes = new PilhaObj<>(10); //O máximo de notificações é 10
    PilhaObj<NotificacaoFeed> pilhaNotificacoesDesfeitas = new PilhaObj<>(10);
    private int qtdNotificacoesDeletadas = 0;

    @PostMapping("/ong/{idOng}")
    public ResponseEntity<Integer> createNotificacao(@PathVariable  Integer idOng) {
        NotificacaoFeed notificacaoFeed = new NotificacaoFeed();
        notificacaoRepository.save(notificacaoFeed);
        enfileirarDoadores(idOng);
        return status(201).body(notificacaoFeed.getId());
    }

    @GetMapping("/{idDoador}")
    public ResponseEntity get10Notificacoes(@PathVariable Integer idDoador) {
        List<ReadNotificacaoFeedDto> nomesOngNotificacoes = notificacaoRepository.findTop10ByInscricaoDoadorCodOrderByDataNotificacao(idDoador);
        if (nomesOngNotificacoes.isEmpty())
            return status(204).build();

        for (ReadNotificacaoFeedDto nomeOng : nomesOngNotificacoes)
            pilhaNotificacoes.push(nomeOng);

        return status(200).body(pilhaNotificacoes);
    }

    @DeleteMapping()
    public ResponseEntity deleteNotificacao() {
        try {
            Optional<NotificacaoFeed> notificacao = notificacaoRepository.findById(pilhaNotificacoes.pop().getId());
            if (notificacao.isEmpty())
                return status(404).build();

            pilhaNotificacoesDesfeitas.push(notificacao.get());
            notificacaoRepository.delete(notificacao.get());
            qtdNotificacoesDeletadas++;
            return status(200).build();
        } catch (IllegalStateException ex) {
            return status(500).body("Máximo de deleções feitas!");
        }
    }

    @PostMapping("/refazer")
    public ResponseEntity refazerNotificacao() {
        if (qtdNotificacoesDeletadas == 0)
            return status(400).body("Nenhuma notificação deletada");

        NotificacaoFeed notificacaoFeed = pilhaNotificacoesDesfeitas.pop();
        ReadNotificacaoFeedDto readNotificacao = new ReadNotificacaoFeedDto(notificacaoFeed.getId(), ongRepository.findNomeByCod(notificacaoFeed.getInscricao().getOng().getCod()));
        pilhaNotificacoes.push(readNotificacao);
        notificacaoRepository.save(notificacaoFeed);
        qtdNotificacoesDeletadas--;
        return status(201).body(notificacaoFeed);
    }
    public void enfileirarDoadores(Integer idOng) {
        List<Inscricao> inscritosOng = inscricaoRepository.findByOngCod(idOng);
        if (inscritosOng.size() == 0)
            return;

        filaInscritos = new FilaObj<>(inscritosOng.size());
        for (Inscricao inscricao : inscritosOng)
            filaInscritos.insert(inscricao);

        while (!filaInscritos.isEmpty())
            notificar(filaInscritos.poll(), idOng);
    }
    public void notificar(Inscricao inscricao, Integer idOng) {
        String mensagem = String.format("A ONG %s fez um novo post!", ongRepository.findNomeByCod(idOng));
        Email email = new Email();
        try {
            email.enviarEmail(mensagem, doadorRepository.findEmailByCod(inscricao.ong.getCod()));
        }catch (Exception ex){
            throw ex;
        }
    }
}