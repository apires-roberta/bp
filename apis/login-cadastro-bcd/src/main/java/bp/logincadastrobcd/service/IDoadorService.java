package bp.logincadastrobcd.service;

import bp.logincadastrobcd.dto.doador.CreateDoador;
import bp.logincadastrobcd.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.dto.usuario.ReadUsuarioDto;
import bp.logincadastrobcd.entidade.Doador;

public interface IDoadorService {

    Integer login(LoginUsuarioDto usuarioLogin);
    String cadastro(CreateDoador novoDoador);

    boolean atualizarFotoDoador(Integer idDoador, byte[] fotoPerfil);

    boolean logoff(Integer idUsuario);

    boolean deletarConta(Integer idUsuario);

    ReadUsuarioDto getDoador(Integer idUsuario);
}
