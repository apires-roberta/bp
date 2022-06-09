package betterplace.betterplacebcd.entidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    Class<Usuario> usuarioClass = Usuario.class;

    @Test
    @DisplayName("campo 'nome' deveria estar anotado com @NotBlank")
    void campoNomeNotBlank() throws NoSuchFieldException {

        Field campoNome = usuarioClass.getDeclaredField("nome");

        assertTrue(campoNome.isAnnotationPresent(NotBlank.class));
    }

    @Test
    @DisplayName("campo 'nome' deveria estar anotado com @NotNull")
    void campoNomeNotNull() throws NoSuchFieldException {

        Field campoNome = usuarioClass.getDeclaredField("nome");

        assertTrue(campoNome.isAnnotationPresent(NotNull.class));
    }

    @Test
    @DisplayName("campo 'nome' deveria estar anotado com @Size(2..45)")
    void campoNomeSize() throws NoSuchFieldException {
        Field campoNome = usuarioClass.getDeclaredField("nome");

        Size size = campoNome.getDeclaredAnnotation(Size.class);

        assertNotNull(size);
        assertEquals(2, size.min());
        assertEquals(45, size.max());
    }

    @Test
    @DisplayName("campo 'email' deveria estar anotado com @NotBlank")
    void campoEmailNotBlank() throws NoSuchFieldException {

        Field campoEmail = usuarioClass.getDeclaredField("email");

        assertTrue(campoEmail.isAnnotationPresent(NotBlank.class));
    }

    @Test
    @DisplayName("campo 'email' deveria estar anotado com @NotNull")
    void campoEmailNotNull() throws NoSuchFieldException {

        Field campoEmail = usuarioClass.getDeclaredField("email");

        assertTrue(campoEmail.isAnnotationPresent(NotNull.class));
    }

    @Test
    @DisplayName("campo 'email' deveria estar anotado com @Email")
    void campoEmail() throws NoSuchFieldException {

        Field campoEmail = usuarioClass.getDeclaredField("email");

        assertTrue(campoEmail.isAnnotationPresent(Email.class));
    }

    @Test
    @DisplayName("campo 'senha' deveria estar anotado com @NotBlank")
    void campoSenhaNotBlank() throws NoSuchFieldException {

        Field campoSenha = usuarioClass.getDeclaredField("senha");

        assertTrue(campoSenha.isAnnotationPresent(NotBlank.class));
    }

    @Test
    @DisplayName("campo 'senha' deveria estar anotado com @NotNull")
    void campoSenhaNotNull() throws NoSuchFieldException {

        Field campoSenha = usuarioClass.getDeclaredField("senha");

        assertTrue(campoSenha.isAnnotationPresent(NotNull.class));
    }

    @Test
    @DisplayName("campo 'senha' deveria estar anotado com @Size(8..16)")
    void campoSenhaSize() throws NoSuchFieldException {
        Field campoSenha = usuarioClass.getDeclaredField("senha");

        Size size = campoSenha.getDeclaredAnnotation(Size.class);

        assertNotNull(size);
        assertEquals(8, size.min());
        assertEquals(16, size.max());
    }

    @Test
    @DisplayName("campo 'usuario' deveria estar anotado com @NotBlank")
    void campoUsuarioNotBlank() throws NoSuchFieldException {

        Field campoUsuario = usuarioClass.getDeclaredField("usuario");

        assertTrue(campoUsuario.isAnnotationPresent(NotBlank.class));
    }

    @Test
    @DisplayName("campo 'usuario' deveria estar anotado com @NotNull")
    void campoUsuarioNotNull() throws NoSuchFieldException {

        Field campoUsuario = usuarioClass.getDeclaredField("usuario");

        assertTrue(campoUsuario.isAnnotationPresent(NotNull.class));
    }

    @Test
    @DisplayName("campo 'usuario' deveria estar anotado com @Size(2..20)")
    void campoUsuarioeSize() throws NoSuchFieldException {
        Field campoUsuario = usuarioClass.getDeclaredField("usuario");

        Size size = campoUsuario.getDeclaredAnnotation(Size.class);

        assertNotNull(size);
        assertEquals(2, size.min());
        assertEquals(20, size.max());
    }

}