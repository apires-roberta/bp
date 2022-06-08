package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.DoadorRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class OngControllerTest {

    @MockBean
    private OngRepository repository;

    @Autowired
    OngController ongController;

    @Test
    @DisplayName("Deve retornar 404 login inválido")
    void login() {
        assertEquals(404, ongController.login("teste@teste.com","12345").getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 400 cadastro inválido")
    void cadastroInvalido() {
        Ong ong = new Ong();
        assertEquals(400, ongController.cadastro(ong).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 204 id não existe")
    void logoff() {
        assertEquals(204, ongController.logoff(0).getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar 404 id não existe")
    void deletarConta() {
        assertEquals(404, ongController.deletarConta(0).getStatusCodeValue());
    }
}