package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {RelatorioController.class})
class RelatorioControllerTest {

    @Autowired
    RelatorioController relatorioController;

    @MockBean
    private CampanhaRepository campanhaRepository;

    @MockBean
    private DoacoesRepository doacoesRepository;

//    @MockBean
//    private DoadorRepository doadorRepository;
//
//    @MockBean
//    private OngRepository ongRepository;

    @Test
    @DisplayName("Retorna 404 montar dados id inválido")
    void montarDadosIdInvalido() {
        assertEquals(404, relatorioController.montarDados(0).getStatusCodeValue());
    }

    @Test
    @DisplayName("Retorna 404 exportar dados id inválido")
    void exportarDadosIdInvalido() {
        assertEquals(404, relatorioController.exportarDados(0).getStatusCodeValue());
    }

    @Test
    @DisplayName("Foto vazia retornar 400")
    void patchFotoVazia() {
        byte[] foto = null;
        assertEquals(400, relatorioController.patchFoto(0,foto).getStatusCodeValue());
    }
}