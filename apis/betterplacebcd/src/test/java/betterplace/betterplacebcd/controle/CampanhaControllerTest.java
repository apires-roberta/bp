package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.data.dto.campanha.CreateCampanhaDto;
import betterplace.betterplacebcd.data.dto.campanha.ReadCampanhaDto;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.services.campanha.CampanhaService;
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

    @MockBean
    private CreateCampanhaDto novaCampanha;

    @MockBean
    private CampanhaService campanhaService;

    LocalDateTime dateTime = LocalDateTime.now();

    @Test
    @DisplayName("Inserir Campanha vazio deve retornar 400")
    void postCampanhaVazia() {

        assertEquals(400, campanhaController.postCampanha(novaCampanha).getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando tiver campanhas 200")
    void getAllCampanhas() {

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<?> resposta = campanhaController.getAllCampanhas();

        assertEquals(200, resposta.getStatusCodeValue());

        //assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando campanhas = null retornar 204")
    void getCampanhasFkOng() {
        campanhaController.postCampanha(novaCampanha);
        assertEquals(204, campanhaController.getCampanhasByFkOng(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando FKOng = null retornar 400")
    void getCampanhasFkOngNull() {
        assertEquals(400, campanhaController.getCampanhasByFkOng(null).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve Retornar 204")
    void alterarValor() {
        assertEquals(204, campanhaController.alterarValor(1,22.2).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve Retornar 400 com id nulo")
    void alterarValorNulo() {
        assertEquals(400, campanhaController.alterarValor(null,22.2).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve Retornar 400 com id negativo")
    void alterarValorNegativo() {
        assertEquals(400, campanhaController.alterarValor(-1,22.2).getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando o id da Campanha é um valor inválido")
    void apagarCampanhaInvalido() {
        assertEquals(400, campanhaController.apagarCampanha(-1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando o id da Campanha é null")
    void apagarCampanhaNull() {
        assertEquals(400, campanhaController.apagarCampanha(null).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 204")
    void apagarCampanha() {
        assertEquals(204, campanhaController.apagarCampanha(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 404 quando não encontrado")
    void getCampanhaById() {
        assertEquals(404, campanhaController.getCampanhaById(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando nulo")
    void getCampanhaByIdNulo() {
        assertEquals(400, campanhaController.getCampanhaById(null).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando inválido")
    void getCampanhaByIdInvalido() {
        assertEquals(400, campanhaController.getCampanhaById(-1).getStatusCodeValue());
    }


    @Test
    @DisplayName("Deve retornar 204")
    void alterarDisponibilidadeCampanha() {
        assertEquals(204, campanhaController.alterarDisponibilidadeCampanha(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando nulo")
    void alterarDisponibilidadeCampanhaNulo() {
        assertEquals(400, campanhaController.alterarDisponibilidadeCampanha(null).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando inválido")
    void alterarDisponibilidadeCampanhaInvalido() {
        assertEquals(400, campanhaController.alterarDisponibilidadeCampanha(-1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 204")
    void getRecomendacoesByIdCampanha() {
        assertEquals(204, campanhaController.getRecomendacoesByIdCampanha(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando nulo")
    void getRecomendacoesByIdCampanhaNulo() {
        CampanhaController cpc = new CampanhaController();
        assertEquals(404, cpc.getRecomendacoesByIdCampanha(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 204")
    void getCampanhasDisponiveis() {
        assertEquals(204, campanhaController.getCampanhasDisponiveis(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando nulo")
    void getCampanhasDisponiveisNulo() {
        assertEquals(400, campanhaController.getCampanhasDisponiveis(null).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando inválido")
    void getCampanhasDisponiveisInvalido() {
        assertEquals(400, campanhaController.getCampanhasDisponiveis(-1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 204")
    void indisponibilizarTodasCampanhasByOng() {
        assertEquals(204, campanhaController.indisponibilizarTodasCampanhasByOng(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando nulo")
    void indisponibilizarTodasCampanhasByOngNulo() {
        assertEquals(400, campanhaController.indisponibilizarTodasCampanhasByOng(null).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando inválido")
    void indisponibilizarTodasCampanhasByOngInvalido() {
        assertEquals(400, campanhaController.indisponibilizarTodasCampanhasByOng(-1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 200")
    void getQuantidadeCampanhasDisponiveisByOng() {
        assertEquals(200, campanhaController.getQuantidadeCampanhasDisponiveisByOng(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando nulo")
    void getQuantidadeCampanhasDisponiveisByOngNulo() {
        assertEquals(400, campanhaController.getQuantidadeCampanhasDisponiveisByOng(null).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando inválido")
    void getQuantidadeCampanhasDisponiveisByOngInvalido() {
        assertEquals(400, campanhaController.getQuantidadeCampanhasDisponiveisByOng(-1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 200")
    void getQuantidadeCampanhasTotalByOng() {
        assertEquals(200, campanhaController.getQuantidadeCampanhasTotalByOng(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando nulo")
    void getQuantidadeCampanhasTotalByOngNulo() {
        assertEquals(400, campanhaController.getQuantidadeCampanhasTotalByOng(null).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando inválido")
    void getQuantidadeCampanhasTotalByOngInvalido() {
        assertEquals(400, campanhaController.getQuantidadeCampanhasTotalByOng(-1).getStatusCodeValue());
    }
}