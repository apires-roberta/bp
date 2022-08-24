package bp.logincadastrobcd.service;

import bp.logincadastrobcd.controle.dto.ong.CreateOng;
import bp.logincadastrobcd.controle.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.controle.dto.usuario.ReadUsuarioDto;
import bp.logincadastrobcd.entidade.Ong;
import bp.logincadastrobcd.repositorio.OngRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OngService implements IOngService{
    @Autowired
    private OngRepository repository;
    @Autowired
    private ModelMapper _mapper;
    @Override
    public Integer login(LoginUsuarioDto usuarioLogin) {
        if (!repository.existsByEmailAndSenha(usuarioLogin.getEmail(), usuarioLogin.getSenha()))
            return null;

        Ong ong = repository.findByEmail(usuarioLogin.getEmail());
        ong.setAutenticado(true);
        repository.save(ong);
        return ong.getCod();
    }
    @Override
    public String cadastro(CreateOng novoUsuario) {
        if (repository.existsByEmail(novoUsuario.getEmail()))
            return "Existente"; //409 - Conflict

        if (novoUsuario.getCnpj() == null || novoUsuario.getEmail() == null || novoUsuario.getSenha() == null)
            return "Bad Request";

        Ong doadorMap = _mapper.map(novoUsuario, Ong.class);
        repository.save(doadorMap);

        return doadorMap.getCod().toString();
    }
    @Override
    public boolean atualizarFotoOng(Integer idUsuario, byte[] fotoPerfil) {
        if (!repository.existsById(idUsuario))
            return false;

        Optional<Ong> ong = repository.findByCod(idUsuario);
        if (ong.isEmpty())
            return false;

        ong.get().setFotoPerfil(fotoPerfil);
        repository.save(ong.get());
        return true;
    }
    @Override
    public boolean logoff(Integer idUsuario) {
        if (!repository.existsByCodAndAutenticadoTrue(idUsuario))
            return false;

        Optional<Ong> ong = repository.findByCod(idUsuario);
        if (ong.isEmpty())
            return false;

        ong.get().setAutenticado(false);
        repository.save(ong.get());
        return true;
    }
    @Override
    public boolean deletarConta(Integer idUsuario) {
        Optional<Ong> ong = repository.findByCod(idUsuario);
        if (ong.isEmpty())
            return false;

        repository.delete(ong.get());
        return true;
    }
    @Override
    public ReadUsuarioDto getOng(Integer idUsuario) {
        Optional<Ong> ong = repository.findByCod(idUsuario);
        if (ong.isEmpty())
            return null;

        ReadUsuarioDto ongDto = _mapper.map(ong.get(), ReadUsuarioDto.class);
        return ongDto;
    }
}