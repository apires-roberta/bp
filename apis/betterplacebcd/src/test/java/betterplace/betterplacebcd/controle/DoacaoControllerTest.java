package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.data.dto.doacao.CreateDoacaoDto;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import betterplace.betterplacebcd.services.doacao.DoacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {DoacaoController.class})
class DoacaoControllerTest {

    @Autowired
    DoacaoController doacaoController;

    @MockBean
    private DoacoesRepository repository;

    @MockBean
    private DoacaoService doacaoService;

    @MockBean
    private CreateDoacaoDto createDoacaoDto;

    LocalDateTime dateTime = LocalDateTime.now();

    @Test
    @DisplayName("Doar deve retornar 201")
    void doar() {
        assertEquals(201, doacaoController.doar(createDoacaoDto).getStatusCodeValue());
    }

    @Test
    @DisplayName("Doar deve retornar 400")
    void doarNull() {
        CreateDoacaoDto createDoacao = null;
        assertEquals(400, doacaoController.doar(createDoacao).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 204")
    void getDoacoesByIdCampanha() {
        assertEquals(204, doacaoController.getDoacoesByIdCampanha(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando nulo")
    void getDoacoesByIdCampanhaNulo() {
        assertEquals(400, doacaoController.getDoacoesByIdCampanha(null).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando inválido")
    void getDoacoesByIdCampanhaInvalido() {
        assertEquals(400, doacaoController.getDoacoesByIdCampanha(-1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 404")
    void getDoacaoById() {
        assertEquals(404, doacaoController.getDoacaoById(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando nulo")
    void getDoacaoByIdNulo() {
        assertEquals(400, doacaoController.getDoacaoById(null).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando inválido")
    void getDoacaoByIdInvalido() {
        assertEquals(400, doacaoController.getDoacaoById(-1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 404")
    void getUltimaDoacaoDoadorCampanha() {
        assertEquals(404, doacaoController.getUltimaDoacaoDoadorCampanha(1,1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando inválido")
    void getUltimaDoacaoDoadorCampanhaInvalido() {
        assertEquals(400, doacaoController.getUltimaDoacaoDoadorCampanha(-1,1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando inválido")
    void getUltimaDoacaoDoadorCampanhaInvalidos() {
        assertEquals(400, doacaoController.getUltimaDoacaoDoadorCampanha(1,-1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 200")
    void getTotalRecebidoOng() {
        assertEquals(200, doacaoController.getTotalRecebidoOng(1).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando nulo")
    void ggetTotalRecebidoOngNulo() {
        assertEquals(400, doacaoController.getTotalRecebidoOng(null).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 quando inválido")
    void getTotalRecebidoOngInvalido() {
        assertEquals(400, doacaoController.getTotalRecebidoOng(-1).getStatusCodeValue());
    }
}