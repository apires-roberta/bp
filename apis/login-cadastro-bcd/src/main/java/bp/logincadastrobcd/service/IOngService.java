package bp.logincadastrobcd.service;

import bp.logincadastrobcd.controle.dto.ong.CreateOng;
import bp.logincadastrobcd.controle.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.controle.dto.usuario.ReadUsuarioDto;

public interface IOngService {

    Integer login(LoginUsuarioDto usuarioLogin);

    String cadastro(CreateOng novoUsuario);

    boolean atualizarFotoOng(Integer idUsuario, byte[] fotoPerfil);

    boolean logoff(Integer idUsuario);

    boolean deletarConta(Integer idUsuario);

    ReadUsuarioDto getOng(Integer idUsuario);
}
