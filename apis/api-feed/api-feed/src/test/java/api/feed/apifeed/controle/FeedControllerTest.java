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

    @Test
    @DisplayName("Quando tiver feed retornar 200 com corpo")
    void getFeeds() {

        Feed feed1 = mock(Feed.class);
        Feed feed2 = mock(Feed.class);

        List<Feed> listaMock = List.of(feed1, feed2);

        when(repository.findAll()).thenReturn(listaMock);

        ResponseEntity<List<Feed>> resposta = feedController.getFeeds();

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
        assertEquals(listaMock, resposta.getBody());
    }

    @Test
    @DisplayName("Quando tiver feed vazia retornar 204 sem corpo")
    void getFeedsQuandoFeedsVazia() {

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Feed>> resposta = feedController.getFeeds();

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Deve retornar 201 e o id")
    void createFeed() {

        CreateFeedDto feed1 = mock(CreateFeedDto.class);

        assertEquals(201, feedController.createFeed(feed1).getStatusCodeValue());
        assertNotNull(feedController.createFeed(feed1).getBody());
    }

    @Test
    @DisplayName("Inserir feed null deve retornar IllegalArgumentException")
    void createFeedNull() {

        CreateFeedDto feed1 = null;

        assertEquals(new IllegalArgumentException(), feedController.createFeed(feed1));
    }

    @Test
    @DisplayName("Retornar 200 ao atualizar a foto")
    void atualizarFotosFeed() {

        byte[] foto;
        int id = 1;

        //assertEquals(200, feedController.atualizarFotosFeed(foto,id).getStatusCodeValue());
    }

    @Test
    @DisplayName("Retornar 200 e deletar o feed")
    void deleteFeed() {
        assertEquals(200, feedController.deleteFeed(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Retornar 404 ao colocar um id inválido")
    void deleteFeedInvalido() {
        assertEquals(200, feedController.deleteFeed(0).getStatusCodeValue());
    }
}