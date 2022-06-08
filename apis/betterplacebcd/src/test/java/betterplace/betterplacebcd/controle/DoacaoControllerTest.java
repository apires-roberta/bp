package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
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

    LocalDateTime dateTime = LocalDateTime.now();

    @Test
    @DisplayName("Doar valor nulo deve retornar 400")
    void doarNull() {
        Doacao doacao = new Doacao();

        assertEquals(400, doacaoController.doar(doacao).getStatusCodeValue());
    }
}