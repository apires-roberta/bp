package bp.logincadastrobcd.controle;

import bp.logincadastrobcd.dto.doador.CreateDoador;
import bp.logincadastrobcd.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.entidade.Doador;
import bp.logincadastrobcd.repositorio.DoadorRepository;
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
        LoginUsuarioDto loginUsuarioDto = new LoginUsuarioDto();
        assertEquals(404, doadorController.login(loginUsuarioDto).getStatusCodeValue());
    }

    @Test
    @DisplayName("cadastroInvalido Deve retornar 400 cadastro inválido")
    void cadastroInvalido() {
        CreateDoador doador = new CreateDoador();

        assertEquals(400, doadorController.cadastro(doador).getStatusCodeValue());
    }

    @Test
    @DisplayName("atualizar foto inválida Deve retornar 404 id inválido")
    void atualizarFotoDoador() {
        byte[] lista = new byte[10];
        assertEquals(404, doadorController.atualizarFotoDoador(0,lista).getStatusCodeValue());
    }

    @Test
    @DisplayName("logoff Deve retornar 404 id não existe")
    void logoff() {
        assertEquals(404, doadorController.logoff(0).getStatusCodeValue());
    }

    @Test
    @DisplayName("deletarConta Deve retornar 404 id não existe")
    void deletarConta() {
        assertEquals(404, doadorController.deletarConta(0).getStatusCodeValue());
    }


    @Test
    @DisplayName("getDoador Deve retornar 404 id não existe")
    void getDoador() {
        assertEquals(404, doadorController.getDoador(0).getStatusCodeValue());
    }
}