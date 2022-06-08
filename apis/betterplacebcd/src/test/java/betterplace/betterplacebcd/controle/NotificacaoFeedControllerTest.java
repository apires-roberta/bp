package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.repositorio.DoadorRepository;
import betterplace.betterplacebcd.repositorio.InscricaoRepository;
import betterplace.betterplacebcd.repositorio.NotificacaoFeedRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {NotificacaoFeedController.class})
class NotificacaoFeedControllerTest {

    @Autowired
    NotificacaoFeedController notificacaoFeedController;

    @MockBean
    private NotificacaoFeedRepository notificacaoRepository;

    @MockBean
    private InscricaoRepository inscricaoRepository;

    @MockBean
    private OngRepository ongRepository;

    @MockBean
    private DoadorRepository doadorRepository;

    @Test
    @DisplayName("Retorna 204 criando notificação com id invalido")
    void createNotificacaoIdInvalido() {
        assertEquals(204, notificacaoFeedController.createNotificacao(0).getStatusCodeValue());
    }

    @Test
    @DisplayName("Retorna 204 pegando notificação com id invalido")
    void get10NotificacoesIdInvalido() {
        assertEquals(204, notificacaoFeedController.get10Notificacoes(0).getStatusCodeValue());
    }

    @Test
    @DisplayName("Retorna 500 deletando notificação quando não existe")
    void deleteNotificacaoNaoExiste() {
        assertEquals(500, notificacaoFeedController.deleteNotificacao().getStatusCodeValue());
    }

    @Test
    @DisplayName("Retorna 400 refazendo notificação quando não existe")
    void refazerNotificacaoNaoExiste() {
        assertEquals(400, notificacaoFeedController.refazerNotificacao().getStatusCodeValue());
    }
}