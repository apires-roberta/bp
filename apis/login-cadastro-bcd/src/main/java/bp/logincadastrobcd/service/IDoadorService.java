package bp.logincadastrobcd.service;

import bp.logincadastrobcd.controle.dto.doador.CreateDoador;
import bp.logincadastrobcd.controle.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.controle.dto.usuario.ReadUsuarioDto;

public interface IDoadorService {

    Integer login(LoginUsuarioDto usuarioLogin);
    String cadastro(CreateDoador novoDoador);

    boolean atualizarFotoDoador(Integer idDoador, byte[] fotoPerfil);

    boolean logoff(Integer idUsuario);

    boolean deletarConta(Integer idUsuario);

    ReadUsuarioDto getDoador(Integer idUsuario);
}