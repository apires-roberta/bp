package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.data.dto.feed.CreateFeedDto;
import betterplace.betterplacebcd.data.dto.ong.OngOnlyCodDto;
import betterplace.betterplacebcd.entidade.Feed;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.FeedRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
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
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {FeedController.class})
class FeedControllerTest {

    @Autowired
    FeedController feedController;

    @MockBean
    private FeedRepository repository;

    @MockBean
    private OngRepository ongRepository;


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
        Ong ong = new Ong();
        ong.setCnpj("1234546577");
        ong.setCod(1);
        ong.setEmail("teste@teste.com");
        ong.setNome("teste");
        ong.setSenha("12345");
        ong.setTelefone("1234556789");
        ong.setUsuario("tttttt");
        CreateFeedDto feed1 = new CreateFeedDto();
        OngOnlyCodDto ongOnlyCodDto = new OngOnlyCodDto();
        ongOnlyCodDto.setCod(1);
        feed1.setOng(ongOnlyCodDto);
        feed1.setDescricao("teste");
        feed1.setDataPublicacao(dateTime);
        ResponseEntity response = feedController.createFeed(feed1);

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Inserir feed vazio deve retornar 400")
    void createFeedVazio() {

        CreateFeedDto feedDto = new CreateFeedDto();
        OngOnlyCodDto ongOnlyCodDto = new OngOnlyCodDto();
        ongOnlyCodDto.setCod(1);
        feedDto.setOng(ongOnlyCodDto);
        feedDto.setDescricao("teste");
        feedDto.setDataPublicacao(dateTime);
        ResponseEntity response = feedController.createFeed(feedDto);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Retornar 404 ao colocar um id inválido")
    void deleteFeedInvalido() {
        assertEquals(404, feedController.deleteFeed(0).getStatusCodeValue());
    }
}