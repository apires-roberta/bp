package com.bp.feedbcd.services.notificacao;

import com.bp.feedbcd.classesed.FilaObj;
import com.bp.feedbcd.classesed.PilhaObj;
import com.bp.feedbcd.data.dto.notificacaofeed.ReadNotificacaoFeedDto;
import com.bp.feedbcd.data.dto.usuario.ReadUsuarioDto;
import com.bp.feedbcd.entidade.Doador;
import com.bp.feedbcd.entidade.Inscricao;
import com.bp.feedbcd.entidade.NotificacaoFeed;
import com.bp.feedbcd.repository.InscricaoRepository;
import com.bp.feedbcd.repository.NotificacaoFeedRepository;
import com.bp.feedbcd.services.email.Email;
import com.bp.feedbcd.servicesreferences.IOngService;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService implements INotificacaoService {
    @Autowired
    private IOngService _ongService;
    @Autowired
    private InscricaoRepository inscricaoRepository;
    @Autowired
    private NotificacaoFeedRepository notificacaoRepository;
    @Autowired
    ModelMapper mapper;

    PilhaObj<ReadNotificacaoFeedDto> pilhaNotificacoes = new PilhaObj<>(10); //O máximo de notificações é 10
    PilhaObj<NotificacaoFeed> pilhaNotificacoesDesfeitas = new PilhaObj<>(10);
    private int qtdNotificacoesDeletadas = 0;

    public void createNotificacao(Integer idOng) {
        if (idOng == null)
            throw new IllegalArgumentException("IdOng não pode ser nulo");

        try {
            ReadUsuarioDto ong = _ongService.getOngById(idOng); //Verifica se ONG existe
            Thread thread = new Thread(() -> {
                enfileirarDoadores(ong);
            });
            thread.start();
        } catch (FeignException.NotFound ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void enfileirarDoadores(ReadUsuarioDto ong) {
        List<Inscricao> inscritosOng = inscricaoRepository.findByOngCod(ong.getCod());
        if (inscritosOng.size() == 0)
            return;

        FilaObj<Doador> filaInscritos = new FilaObj<>(inscritosOng.size());
        for (Inscricao inscricao : inscritosOng) {
            filaInscritos.insert(inscricao.getDoador());
            NotificacaoFeed notificacaoFeed = new NotificacaoFeed(inscricao);
            notificacaoRepository.save(notificacaoFeed);
        }

        while (!filaInscritos.isEmpty())
            try {
                notificar("Novo post da ong " + ong.getNome() + "!", "Venha Conferir as novidades", filaInscritos.poll());
            } catch (FeignException.NotFound ex) {
                throw ex;
            } catch (Exception ex) {
                continue;
            }
    }

    private void notificar(String assunto, String mensagem, Doador doador) {
        Email email = new Email();

        try {
            email.enviarEmail(assunto, mensagem, doador.getEmail());
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Object> get10Notificacoes(Integer idDoador) {
        List<NotificacaoFeed> listaNotificacoes = notificacaoRepository.findTop10ByInscricaoDoadorCodOrderByDataNotificacao(idDoador);
        pilhaNotificacoes.clear();
        if (listaNotificacoes.isEmpty())
            return null;

        for (NotificacaoFeed notifacao : listaNotificacoes) {
            pilhaNotificacoes.push(mapper.map(notifacao, ReadNotificacaoFeedDto.class));
        }

        return pilhaNotificacoes.toList();
    }

    public void deleteNotificacao() {
        try {
            Optional<NotificacaoFeed> notificacao = notificacaoRepository.findById(pilhaNotificacoes.pop().getId());
            if (notificacao.isEmpty()) throw new IllegalStateException();

            pilhaNotificacoesDesfeitas.push(notificacao.get());
            notificacaoRepository.delete(notificacao.get());
            qtdNotificacoesDeletadas++;
        } catch (IllegalStateException ex) {
            throw ex;
        }
    }

    public void redoNotificacao() {
        try {
            NotificacaoFeed notificacaoFeed = pilhaNotificacoesDesfeitas.pop();
            ReadUsuarioDto ong = _ongService.getOngById(notificacaoFeed.getInscricao().getOng().getCod());

            ReadNotificacaoFeedDto readNotificacao = new ReadNotificacaoFeedDto(notificacaoFeed.getId(), ong.getNome());
            pilhaNotificacoes.push(mapper.map(notificacaoFeed, ReadNotificacaoFeedDto.class));
            notificacaoRepository.save(notificacaoFeed);
            qtdNotificacoesDeletadas--;
        } catch (FeignException.NotFound ex) {
            throw ex;
        }
    }
}