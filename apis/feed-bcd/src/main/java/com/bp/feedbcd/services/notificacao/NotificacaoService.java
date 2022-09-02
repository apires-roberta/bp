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

    private FilaObj<Doador> filaInscritos;
    PilhaObj<ReadNotificacaoFeedDto> pilhaNotificacoes = new PilhaObj<>(10); //O máximo de notificações é 10
    PilhaObj<NotificacaoFeed> pilhaNotificacoesDesfeitas = new PilhaObj<>(10);
    private int qtdNotificacoesDeletadas = 0;


    public void createNotificacao(Integer idOng) {
        if (idOng == null)
            throw new IllegalArgumentException("IdOng não pode ser nulo");

        try {
            _ongService.getOngById(idOng); //Verifica se ONG existe
            enfileirarDoadores(idOng);
        } catch (FeignException.NotFound ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void enfileirarDoadores(Integer idOng) {
        List<Inscricao> inscritosOng = inscricaoRepository.findByOngCod(idOng);
        if (inscritosOng.size() == 0)
            return;

        filaInscritos = new FilaObj<>(inscritosOng.size());
        for (Inscricao inscricao : inscritosOng) {
            filaInscritos.insert(inscricao.getDoador());
            NotificacaoFeed notificacaoFeed = new NotificacaoFeed(inscricao);
            notificacaoRepository.save(notificacaoFeed);
        }

        while (!filaInscritos.isEmpty())
            try {
                notificar(filaInscritos.poll(), idOng);
            }catch (FeignException.NotFound ex){
                throw ex;
            }catch (Exception ex){
                continue;
            }
    }

    private void notificar(Doador doador, Integer idOng) {
        ReadUsuarioDto ong = _ongService.getOngById(idOng);

        String mensagem = String.format("A ONG %s fez um novo post!", ong.getNome());
        Email email = new Email();
        try {
            email.enviarEmail(mensagem, doador.getEmail());
        }catch (Exception ex){
            throw ex;
        }
    }
}
