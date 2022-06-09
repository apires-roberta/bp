package betterplace.betterplacebcd.entidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class FeedTest {

    Class<Feed> feedClass = Feed.class;

    @Test
    @DisplayName("campo 'descricao' deveria estar anotado com @NotBlank")
    void campoDescricaoNotBlank() throws NoSuchFieldException {

        Field campoDescricao = feedClass.getDeclaredField("descricao");

        assertTrue(campoDescricao.isAnnotationPresent(NotBlank.class));
    }

    @Test
    @DisplayName("campo 'descricao' deveria estar anotado com @NotNull")
    void campoDescricaoNotNull() throws NoSuchFieldException {

        Field campoDescricao = feedClass.getDeclaredField("descricao");

        assertTrue(campoDescricao.isAnnotationPresent(NotNull.class));
    }

}