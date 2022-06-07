package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.repositorio.DoadorRepository;
import betterplace.betterplacebcd.repositorio.FeedRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {DoadorController.class})
class DoadorControllerTest {

    @MockBean
    private DoadorRepository repository;

    @Autowired
    DoadorController doadorController;

    @Test
    @DisplayName("Deve retornar 404 login inválido")
    void loginInvalido() {
        assertEquals(404, doadorController.login("fulano@123.com","12345").getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 cadastro inválido")
    void cadastroInvalido() {
        Doador doador = new Doador();
        assertEquals(400, doadorController.cadastro(doador).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 204 id não existe")
    void logoff() {
        assertEquals(204, doadorController.logoff(0).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 404 id não existe")
    void deletarConta() {
        assertEquals(404, doadorController.deletarConta(0).getStatusCodeValue());
    }
}