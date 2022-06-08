package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.data.dto.feed.CreateFeedDto;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.entidade.Feed;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.repositorio.FeedRepository;
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

@SpringBootTest(classes = {CampanhaController.class})
class CampanhaControllerTest {

    @Autowired
    CampanhaController campanhaController;

    @MockBean
    private CampanhaRepository repository;

    LocalDateTime dateTime = LocalDateTime.now();

    @Test
    @DisplayName("Deve retornar 201")
    void postCampanha() {

        Campanha campanha = new Campanha();
        campanha.setIdCampanha(1);
        campanha.setDescCampanha("teste");
        campanha.setNomeCampanha("Nome");
        campanha.setDataCriacao(dateTime);

        assertEquals(201, campanhaController.postCampanha(campanha).getStatusCodeValue());
    }

    @Test
    @DisplayName("Inserir Campanha vazio deve retornar 400")
    void postCampanhaVazia() {

        Campanha campanha = new Campanha();

        assertEquals(400, campanhaController.postCampanha(campanha).getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando tiver campanhas vazia retornar 204 sem corpo")
    void getTudoVazio() {

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<List<Campanha>> resposta = campanhaController.getTudo();

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando o id da Campanha é um valor iválido")
    void alterarValorInvalido() {
        assertEquals(204, campanhaController.alterarValor(1,22.2).getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando o id da Campanha é um valor inválido")
    void apagarVakinhaInvalido() {
        assertEquals(404, campanhaController.apagarCampanha(1).getStatusCodeValue());
    }
}