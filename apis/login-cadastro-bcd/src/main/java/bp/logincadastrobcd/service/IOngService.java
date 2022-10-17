package bp.logincadastrobcd.service;

import bp.logincadastrobcd.dto.ong.CreateOng;
import bp.logincadastrobcd.dto.ong.ReadOngDto;
import bp.logincadastrobcd.dto.ong.UpdateOngDto;
import bp.logincadastrobcd.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.dto.usuario.ReadUsuarioDto;

import java.util.List;

public interface IOngService {

    Integer login(LoginUsuarioDto usuarioLogin);

    String cadastro(CreateOng novoUsuario);

    //boolean atualizarFotoOng(Integer idUsuario, byte[] fotoPerfil);

    boolean logoff(Integer idUsuario);

    boolean deletarConta(Integer idUsuario);

    ReadOngDto getOng(Integer idUsuario);

    List<ReadOngDto> getOngsByNome(String nomeOng);

    boolean atualizarDadosCadastrais(Integer idUsuario, UpdateOngDto ongAtualizada);
}
