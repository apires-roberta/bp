package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.Email;
import betterplace.betterplacebcd.classes.FilaObj;
import betterplace.betterplacebcd.classes.PilhaObj;
import betterplace.betterplacebcd.data.dto.inscricao.ReadInscricaoDto;
import betterplace.betterplacebcd.data.dto.notificacaofeed.ReadNotificacaoFeedDto;
import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.entidade.Inscricao;
import betterplace.betterplacebcd.entidade.NotificacaoFeed;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.DoadorRepository;
import betterplace.betterplacebcd.repositorio.NotificacaoFeedRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
import betterplace.betterplacebcd.repositorio.InscricaoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    @Autowired
    ModelMapper mapper;

    private FilaObj<Doador> filaInscritos;
    PilhaObj<ReadNotificacaoFeedDto> pilhaNotificacoes = new PilhaObj<>(10); //O máximo de notificações é 10
    PilhaObj<NotificacaoFeed> pilhaNotificacoesDesfeitas = new PilhaObj<>(10);
    private int qtdNotificacoesDeletadas = 0;

    @PostMapping("/ong/{idOng}")
    public ResponseEntity<Integer> createNotificacao(@PathVariable  Integer idOng) {
        NotificacaoFeed notificacaoFeed = new NotificacaoFeed();
        notificacaoRepository.save(notificacaoFeed);
        enfileirarDoadores(idOng);
        pilhaNotificacoes.push(mapper.map(notificacaoFeed, ReadNotificacaoFeedDto.class));
        return status(201).build();
    }

    @GetMapping("")
    public ResponseEntity getNotificacoes(){
        return status(200).body(notificacaoRepository.findAll());
    }

    @GetMapping("/{idDoador}")
    public ResponseEntity get10Notificacoes(@PathVariable Integer idDoador) {
        List<NotificacaoFeed> listaNotificacoes = notificacaoRepository.findTop10ByInscricaoDoadorCodOrderByDataNotificacao(idDoador);
        if (listaNotificacoes.isEmpty())
            return status(204).build();

        List<ReadNotificacaoFeedDto> nomesOngNotificacoes = new ArrayList<>();
        for (NotificacaoFeed notifacao : listaNotificacoes) {
            nomesOngNotificacoes.add(mapper.map(notifacao, ReadNotificacaoFeedDto.class));
        }

        for (ReadNotificacaoFeedDto nomeOng : nomesOngNotificacoes)
            pilhaNotificacoes.push(nomeOng);

        return status(200).body(nomesOngNotificacoes);
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
            return status(400).body("Nenhuma Notificação foi deletada");
        }
    }

    @PostMapping("/refazer")
    public ResponseEntity refazerNotificacao() {
        if (qtdNotificacoesDeletadas == 0)
            return status(400).body("Nenhuma notificação deletada");

        NotificacaoFeed notificacaoFeed = pilhaNotificacoesDesfeitas.pop();
        Optional<Ong> ong = ongRepository.findByCod(notificacaoFeed.getInscricao().getOng().getCod());
        if (ong.isEmpty())
            return status(404).build();

        ReadNotificacaoFeedDto readNotificacao = new ReadNotificacaoFeedDto(notificacaoFeed.getId(), ong.get().getNome());
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
            filaInscritos.insert(inscricao.getDoador());

        while (!filaInscritos.isEmpty())
            notificar(filaInscritos.poll(), idOng);
    }
    public void notificar(Doador doador, Integer idOng) {
        Optional<Ong> ong = ongRepository.findByCod(idOng);
        if (ong.isEmpty())
            return;
        String mensagem = String.format("A ONG %s fez um novo post!", ong.get().getNome());
        Email email = new Email();
        try {
            email.enviarEmail(mensagem, doador.getEmail());
        }catch (Exception ex){
            throw ex;
        }
    }
}