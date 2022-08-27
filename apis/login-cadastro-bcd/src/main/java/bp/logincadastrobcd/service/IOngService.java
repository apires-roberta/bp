package bp.logincadastrobcd.service;

import bp.logincadastrobcd.dto.ong.CreateOng;
import bp.logincadastrobcd.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.dto.usuario.ReadUsuarioDto;

public interface IOngService {

    Integer login(LoginUsuarioDto usuarioLogin);

    String cadastro(CreateOng novoUsuario);

    boolean atualizarFotoOng(Integer idUsuario, byte[] fotoPerfil);

    boolean logoff(Integer idUsuario);

    boolean deletarConta(Integer idUsuario);

    ReadUsuarioDto getOng(Integer idUsuario);
}