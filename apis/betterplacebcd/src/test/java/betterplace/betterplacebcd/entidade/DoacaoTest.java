package betterplace.betterplacebcd.entidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class DoacaoTest {

    Class<Doacao> doacaoClass = Doacao.class;

    @Test
    @DisplayName("campo 'valorDoacao' deveria estar anotado com @NotNull")
    void campoValorDoacao() throws NoSuchFieldException {

        Field campoValorDoacao = doacaoClass.getDeclaredField("valorDoacao");

        assertTrue(campoValorDoacao.isAnnotationPresent(NotNull.class));
    }

    @Test
    @DisplayName("campo 'dataDoacao' deveria estar anotado com @NotNull")
    void campoDataDoacao() throws NoSuchFieldException {

        Field campoDataDoacao = doacaoClass.getDeclaredField("dataDoacao");

        assertTrue(campoDataDoacao.isAnnotationPresent(NotNull.class));
    }

}