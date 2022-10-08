package bp.logincadastrobcd.service;

import bp.logincadastrobcd.dto.ong.CreateOng;
import bp.logincadastrobcd.dto.ong.UpdateOngDto;
import bp.logincadastrobcd.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.dto.usuario.ReadUsuarioDto;
import bp.logincadastrobcd.entidade.Doador;
import bp.logincadastrobcd.entidade.Ong;
import bp.logincadastrobcd.repositorio.OngRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OngService implements IOngService{
    @Autowired
    private OngRepository _repository;
    @Autowired
    private ModelMapper _mapper;
    @Override
    public Integer login(LoginUsuarioDto usuarioLogin) {
        if (!_repository.existsByEmailAndSenha(usuarioLogin.getEmail(), usuarioLogin.getSenha()))
            return null;

        Ong ong = _repository.findByEmail(usuarioLogin.getEmail());
        ong.setAutenticado(true);
        _repository.save(ong);
        return ong.getCod();
    }
    @Override
    public String cadastro(CreateOng novoUsuario) {
        if (_repository.existsByEmail(novoUsuario.getEmail()))
            return "Existente"; //409 - Conflict

        if (novoUsuario.getCnpj() == null || novoUsuario.getEmail() == null || novoUsuario.getSenha() == null)
            return "Bad Request";

        Ong doadorMap = _mapper.map(novoUsuario, Ong.class);
        _repository.save(doadorMap);

        return doadorMap.getCod().toString();
    }
    @Override
    public boolean atualizarFotoOng(Integer idUsuario, byte[] fotoPerfil) {
        if (!_repository.existsById(idUsuario))
            return false;

        Optional<Ong> ong = _repository.findByCod(idUsuario);
        if (ong.isEmpty())
            return false;

        ong.get().setFotoPerfil(fotoPerfil);
        _repository.save(ong.get());
        return true;
    }
    @Override
    public boolean logoff(Integer idUsuario) {
        if (!_repository.existsByCodAndAutenticadoTrue(idUsuario))
            return false;

        Optional<Ong> ong = _repository.findByCod(idUsuario);
        if (ong.isEmpty())
            return false;

        ong.get().setAutenticado(false);
        _repository.save(ong.get());
        return true;
    }
    @Override
    public boolean deletarConta(Integer idUsuario) {
        Optional<Ong> ong = _repository.findByCod(idUsuario);
        if (ong.isEmpty())
            return false;

        _repository.delete(ong.get());
        return true;
    }
    @Override
    public ReadUsuarioDto getOng(Integer idUsuario) {
        Optional<Ong> ong = _repository.findByCod(idUsuario);
        if (ong.isEmpty())
            return null;

        ReadUsuarioDto ongDto = _mapper.map(ong.get(), ReadUsuarioDto.class);
        return ongDto;
    }

    @Override
    public List<ReadUsuarioDto> getOngsByNome(String nomeOng) {
        List<Ong> ongs = _repository.findByNomeContains(nomeOng);

        if (ongs.isEmpty())
            return null;

        List<ReadUsuarioDto> ongsDto = new ArrayList<>();

        for (Ong ong : ongs) {
            ongsDto.add(_mapper.map(ong,ReadUsuarioDto.class));
        }

        return ongsDto;
    }

    @Override
    public boolean atualizarDadosCadastrais(Integer idUsuario, UpdateOngDto ongAtualizada) {
        Optional<Ong> ongAtualOpt = _repository.findByCod(idUsuario);
        if (ongAtualOpt.isEmpty())
            return false;

        Ong ongAtual = ongAtualOpt.get();
        _mapper.map(ongAtualizada, ongAtual);
        _repository.save(ongAtual);

        return true;
    }
}