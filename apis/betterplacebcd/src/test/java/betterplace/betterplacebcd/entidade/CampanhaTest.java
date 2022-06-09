package betterplace.betterplacebcd.entidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CampanhaTest {

    Class<Campanha> campanhaClass = Campanha.class;

    @Test
    @DisplayName("campo 'nome' deveria estar anotado com @NotBlank")
    void campoNomeNotBlank() throws NoSuchFieldException {

        Field campoNome = campanhaClass.getDeclaredField("nomeCampanha");

        assertTrue(campoNome.isAnnotationPresent(NotBlank.class));
    }

    @Test
    @DisplayName("campo 'nome' deveria estar anotado com @NotNull")
    void campoNomeNotNull() throws NoSuchFieldException {

        Field campoNome = campanhaClass.getDeclaredField("nomeCampanha");

        assertTrue(campoNome.isAnnotationPresent(NotNull.class));
    }

    @Test
    @DisplayName("campo 'nome' deveria estar anotado com @Size(2..45)")
    void campoNomeSize() throws NoSuchFieldException {
        Field campoNome = campanhaClass.getDeclaredField("nomeCampanha");

        Size size = campoNome.getDeclaredAnnotation(Size.class);

        assertNotNull(size);
        assertEquals(2, size.min());
        assertEquals(45, size.max());
    }

    @Test
    @DisplayName("campo 'nomeItem' deveria estar anotado com @NotBlank")
    void campoNomeItemNotBlank() throws NoSuchFieldException {

        Field campoNomeItem = campanhaClass.getDeclaredField("nomeItem");

        assertTrue(campoNomeItem.isAnnotationPresent(NotBlank.class));
    }

    @Test
    @DisplayName("campo 'nome' deveria estar anotado com @NotNull")
    void campoNomeItemNotNull() throws NoSuchFieldException {

        Field campoNomeItem = campanhaClass.getDeclaredField("nomeItem");

        assertTrue(campoNomeItem.isAnnotationPresent(NotNull.class));
    }

    @Test
    @DisplayName("campo 'nomeItem' deveria estar anotado com @Size(2..25)")
    void campoNomeItemSize() throws NoSuchFieldException {
        Field campoNomeItem = campanhaClass.getDeclaredField("nomeItem");

        Size size = campoNomeItem.getDeclaredAnnotation(Size.class);

        assertNotNull(size);
        assertEquals(2, size.min());
        assertEquals(25, size.max());
    }

    @Test
    @DisplayName("campo 'descCampanha' deveria estar anotado com @NotBlank")
    void campoDescCampanhaBlank() throws NoSuchFieldException {

        Field campoDescCampanha = campanhaClass.getDeclaredField("descCampanha");

        assertTrue(campoDescCampanha.isAnnotationPresent(NotBlank.class));
    }

    @Test
    @DisplayName("campo 'descCampanha' deveria estar anotado com @Size(2..100)")
    void campoDescCampanha() throws NoSuchFieldException {
        Field campoDescCampanha = campanhaClass.getDeclaredField("descCampanha");

        Size size = campoDescCampanha.getDeclaredAnnotation(Size.class);

        assertNotNull(size);
        assertEquals(2, size.min());
        assertEquals(100, size.max());
    }

    @Test
    @DisplayName("campo 'descCampanha' deveria estar anotado com @NotNull")
    void campoDescCampanhaNotNull() throws NoSuchFieldException {

        Field campoDescCampanha = campanhaClass.getDeclaredField("descCampanha");

        assertTrue(campoDescCampanha.isAnnotationPresent(NotNull.class));
    }

    @Test
    @DisplayName("campo 'valorNecessario' deveria estar anotado com @NotNull")
    void campoValorNecessario() throws NoSuchFieldException {

        Field campoValorNecessario = campanhaClass.getDeclaredField("valorNecessario");

        assertTrue(campoValorNecessario.isAnnotationPresent(NotNull.class));
    }

    @Test
    @DisplayName("campo 'dataCriacao' deveria estar anotado com @NotNull")
    void campoDataCriacao() throws NoSuchFieldException {

        Field campoDataCriacao = campanhaClass.getDeclaredField("dataCriacao");

        assertTrue(campoDataCriacao.isAnnotationPresent(NotNull.class));
    }
}