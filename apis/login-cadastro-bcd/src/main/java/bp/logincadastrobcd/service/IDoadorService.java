package bp.logincadastrobcd.service;

import bp.logincadastrobcd.dto.doador.CreateDoador;
import bp.logincadastrobcd.dto.doador.UpdateDoadorDto;
import bp.logincadastrobcd.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.dto.usuario.ReadUsuarioDto;

import java.util.List;

public interface IDoadorService {

    Integer login(LoginUsuarioDto usuarioLogin);
    String cadastro(CreateDoador novoDoador);

    boolean atualizarFotoDoador(Integer idDoador, byte[] fotoPerfil);

    boolean logoff(Integer idUsuario);

    boolean deletarConta(Integer idUsuario);

    ReadUsuarioDto getDoador(Integer idUsuario);

    List<ReadUsuarioDto> getDoadorByNome(String nomeDoador);

    boolean atualizarDadosCadastrais(Integer idUsuario, UpdateDoadorDto doadorAtualizado);
}