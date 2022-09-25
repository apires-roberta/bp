package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.data.dto.campanha.CreateCampanhaDto;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
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
    @DisplayName("Inserir Campanha vazio deve retornar 400")
    void postCampanhaVazia() {

        CreateCampanhaDto campanha = new CreateCampanhaDto();

        assertEquals(400, campanhaController.postCampanha(campanha).getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando tiver campanhas vazia retornar 204 sem corpo")
    void getTudoVazio() {

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity<?> resposta = campanhaController.getAllCampanhas();

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando o id da Campanha é um valor iválido")
    void alterarValorInvalido() {
        assertEquals(404, campanhaController.alterarValor(1,22.2).getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando o id da Campanha é um valor inválido")
    void apagarVakinhaInvalido() {
        assertEquals(404, campanhaController.apagarCampanha(1).getStatusCodeValue());
    }
}