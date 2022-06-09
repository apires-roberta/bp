package betterplace.betterplacebcd.entidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class DoadorTest {

    Class<Doador> doadorClass = Doador.class;

    @Test
    @DisplayName("campo 'cpf' deveria estar anotado com @NotNull")
    void campoCpf() throws NoSuchFieldException {

        Field campoCpf = doadorClass.getDeclaredField("cpf");

        assertTrue(campoCpf.isAnnotationPresent(NotNull.class));
    }

}