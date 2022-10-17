package bp.logincadastrobcd.controle;

import bp.logincadastrobcd.dto.ong.CreateOng;
import bp.logincadastrobcd.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.entidade.Usuario;
import bp.logincadastrobcd.repositorio.OngRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = {OngController.class})
class OngControllerTest {

    @MockBean
    private OngRepository repository;

    @MockBean
    private Usuario usuario;

    @Autowired
    OngController ongController;

    @Test
    @DisplayName("Deve retornar 404 login inválido")
    void loginInvalido() {
        LoginUsuarioDto loginUsuarioDto = new LoginUsuarioDto();
        assertEquals(404, ongController.login(loginUsuarioDto).getStatusCodeValue());
    }

    @Test
    @DisplayName("cadastro Válido Deve retornar 201 cadastro inválido")
    void cadastroValido() {
        CreateOng ong = new CreateOng();
        ong.setUsuario(usuario.getUsuario());
        ong.setCnpj("12345678");
        ong.setEmail("teste@teste.com");
        ong.setNome("Teste");
        ong.setTelefone("1192005-7526");
        ong.setSenha("12345");

        assertEquals(201, ongController.cadastro(ong).getStatusCodeValue());
    }

    @Test
    @DisplayName("cadastro Invalido Deve retornar 400 cadastro inválido")
    void cadastroInvalido() {
        CreateOng createOng = new CreateOng();

        assertEquals(400, ongController.cadastro(createOng).getStatusCodeValue());
    }

    @Test
    @DisplayName("logoff Deve retornar 404 id não existe")
    void logoff() {
        assertEquals(404, ongController.logoff(0).getStatusCodeValue());
    }

    @Test
    @DisplayName("deletarConta Deve retornar 404 id não existe")
    void deletarConta() {
        assertEquals(404, ongController.deletarConta(0).getStatusCodeValue());
    }


    @Test
    @DisplayName("getDoador Deve retornar 404 id não existe")
    void getDoador() {
        assertEquals(404, ongController.getOng(0).getStatusCodeValue());
    }
}