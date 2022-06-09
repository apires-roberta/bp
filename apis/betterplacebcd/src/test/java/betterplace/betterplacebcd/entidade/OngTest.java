package betterplace.betterplacebcd.entidade;

import org.hibernate.validator.constraints.br.CNPJ;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class OngTest {

    Class<Ong> ongClass = Ong.class;

    @Test
    @DisplayName("campo 'cnpj' deveria estar anotado com @CNPJ")
    void campoCNPJ() throws NoSuchFieldException {

        Field campoCNPJ = ongClass.getDeclaredField("cnpj");

        assertTrue(campoCNPJ.isAnnotationPresent(CNPJ.class));
    }

    @Test
    @DisplayName("campo 'cnpj' deveria estar anotado com @NotNull")
    void campoCNPJNotNull() throws NoSuchFieldException {

        Field campoCNPJ = ongClass.getDeclaredField("cnpj");

        assertTrue(campoCNPJ.isAnnotationPresent(NotNull.class));
    }

}