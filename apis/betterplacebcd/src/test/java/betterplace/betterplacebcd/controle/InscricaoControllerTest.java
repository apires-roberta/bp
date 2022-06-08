package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.data.dto.inscricao.CreateInscricaoDto;
import betterplace.betterplacebcd.repositorio.DoadorRepository;
import betterplace.betterplacebcd.repositorio.InscricaoRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {InscricaoController.class})
class InscricaoControllerTest {

    @Autowired
    InscricaoController inscricaoController;

    @MockBean
    private InscricaoRepository inscricaoRepository;

    @MockBean
    private OngRepository ongRepository;

    @MockBean
    private DoadorRepository doadorRepository;

    @Test
    @DisplayName("Retorna 204 pegando inscrição com id invalido")
    void getInscritosOngInvalida() {
        assertEquals(204, inscricaoController.getInscritosOng(0).getStatusCodeValue());
    }

    @Test
    @DisplayName("Retorna 404 criando inscrição com id invalido")
    void createInscricaoIdInvalido() {
        CreateInscricaoDto createInscricaoDto = new CreateInscricaoDto();
        //createInscricaoDto.setFkDoador(0);
        //createInscricaoDto.setFkOng(0);
        assertEquals(404, inscricaoController.createInscricao(createInscricaoDto).getStatusCodeValue());
    }
}