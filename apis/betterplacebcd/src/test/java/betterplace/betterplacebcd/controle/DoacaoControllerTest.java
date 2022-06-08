package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {DoacaoController.class})
class DoacaoControllerTest {

    @Autowired
    DoacaoController doacaoController;

    @MockBean
    private DoacoesRepository repository;

    LocalDateTime dateTime = LocalDateTime.now();

    @Test
    @DisplayName("Doar valor deve retonar 400")
    void doarNull() {
        Doacao doacao = new Doacao();
        doacao.setIdDoacao(1);
        doacao.setDataDoacao(dateTime);
        doacao.setValorDoacao(0.0);
        Doador doador = new Doador();
        doador.setCod(1);
        doador.setEmail("teste@teste.com");
        doador.setSenha("12345");
        doador.setCpf("123456789");
        doador.setNome("teste");
        doador.setTelefone("1234567890");
        doador.setUsuario("teste");
        doacao.setDoador(doador);
        Ong ong = new Ong();
        ong.setCnpj("1234546577");
        ong.setCod(1);
        ong.setEmail("teste@teste.com");
        ong.setNome("teste");
        ong.setSenha("12345");
        ong.setTelefone("1234556789");
        ong.setUsuario("tttttt");
        doacao.setOng(ong);
        Campanha campanha = new Campanha();
        campanha.setIdCampanha(1);
        campanha.setNomeCampanha("Ajduar");
        campanha.setDescCampanha("Ajduar crianças");
        campanha.setDataCriacao(dateTime);
        campanha.setOng(ong);
        campanha.setNomeItem("teste");
        doacao.setCampanha(campanha);
        assertEquals(400, doacaoController.doar(doacao).getStatusCodeValue());
    }
    @Test
    @DisplayName("Doar valor deve retonar 201")
    void doarValor() {
        Doacao doacao = new Doacao();
        doacao.setIdDoacao(1);
        doacao.setDataDoacao(dateTime);
        doacao.setValorDoacao(22.2);
        Doador doador = new Doador();
        doador.setCod(1);
        doador.setEmail("teste@teste.com");
        doador.setSenha("12345");
        doador.setCpf("123456789");
        doador.setNome("teste");
        doador.setTelefone("1234567890");
        doador.setUsuario("teste");
        doacao.setDoador(doador);
        Ong ong = new Ong();
        ong.setCnpj("1234546577");
        ong.setCod(1);
        ong.setEmail("teste@teste.com");
        ong.setNome("teste");
        ong.setSenha("12345");
        ong.setTelefone("1234556789");
        ong.setUsuario("tttttt");
        doacao.setOng(ong);
        Campanha campanha = new Campanha();
        campanha.setIdCampanha(1);
        campanha.setNomeCampanha("Ajduar");
        campanha.setDescCampanha("Ajduar crianças");
        campanha.setDataCriacao(dateTime);
        campanha.setOng(ong);
        campanha.setNomeItem("teste");
        doacao.setCampanha(campanha);
        assertEquals(201, doacaoController.doar(doacao).getStatusCodeValue());
    }
}