package api.feed.apifeed.controle;

import api.feed.apifeed.dto.feed.CreateFeedDto;
import api.feed.apifeed.entidade.Feed;
import api.feed.apifeed.repositorio.FeedRepository;
import api.feed.apifeed.services.OngService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {FeedController.class})
class FeedControllerTest {

    @Autowired
    FeedController feedController;

    @MockBean
    private FeedRepository repository;

    @MockBean
    private OngService ongService;

    LocalDateTime dateTime = LocalDateTime.now();

    @Test
    @DisplayName("Quando tiver feed vazia retornar 204 sem corpo")
    void getFeedsQuandoFeedsVazia() {

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Feed>> resposta = feedController.getFeeds();

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Deve retornar 201")
    void createFeed() {

        CreateFeedDto feed1 = new CreateFeedDto();
        feed1.setFkOng(1);
        feed1.setDescricao("teste");
        feed1.setDataPublicacao(dateTime);

        assertEquals(201, feedController.createFeed(feed1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Inserir feed vazio deve retornar 400")
    void createFeedVazio() {

        CreateFeedDto feedDto = new CreateFeedDto();

        assertEquals(400, feedController.createFeed(feedDto).getStatusCodeValue());
    }

    @Test
    @DisplayName("Retornar 404 ao colocar um id inválido")
    void deleteFeedInvalido() {
        assertEquals(404, feedController.deleteFeed(0).getStatusCodeValue());
    }
}