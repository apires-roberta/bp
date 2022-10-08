package bp.logincadastrobcd.service;

import bp.logincadastrobcd.dto.doador.CreateDoador;
import bp.logincadastrobcd.dto.doador.UpdateDoadorDto;
import bp.logincadastrobcd.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.dto.usuario.ReadUsuarioDto;
import bp.logincadastrobcd.entidade.Doador;
import bp.logincadastrobcd.repositorio.DoadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin
public class DoadorService implements IDoadorService {
    @Autowired
    private DoadorRepository _repository;
    @Autowired
    private ModelMapper _mapper;

    @Override
    public Integer login(LoginUsuarioDto usuarioLogin) {
        if (!_repository.existsByEmailAndSenha(usuarioLogin.getEmail(), usuarioLogin.getSenha()))
            return null;

        Doador doador = _repository.findByEmail(usuarioLogin.getEmail());
        doador.setAutenticado(true);
        _repository.save(doador);
        return doador.getCod();
    }

    @Override
    public String cadastro(CreateDoador novoDoador) {
        if (_repository.existsByEmail(novoDoador.getEmail()))
            return "Existente"; //409 - Conflict

        if (novoDoador.getCpf() == null || novoDoador.getEmail() == null || novoDoador.getSenha() == null)
            return "Bad Request";

        Doador doadorMap = _mapper.map(novoDoador, Doador.class);
        _repository.save(doadorMap);

        return doadorMap.getCod().toString();
    }

    @Override
    public boolean atualizarFotoDoador(Integer idDoador, byte[] fotoPerfil) {
        if (!_repository.existsById(idDoador))
            return false;

        Optional<Doador> doador = _repository.findByCod(idDoador);
        if (doador.isEmpty())
            return false;

        doador.get().setFotoPerfil(fotoPerfil);
        _repository.save(doador.get());
        return true;
    }

    @Override
    public boolean logoff(Integer idUsuario) {
        if (!_repository.existsByCodAndAutenticadoTrue(idUsuario))
            return false;

        Optional<Doador> doador = _repository.findByCod(idUsuario);
        if (doador.isEmpty())
            return false;

        doador.get().setAutenticado(false);
        _repository.save(doador.get());
        return true;
    }

    @Override
    public boolean deletarConta(Integer idUsuario) {
        Optional<Doador> doador = _repository.findByCod(idUsuario);
        if (doador.isEmpty())
            return false;

        _repository.delete(doador.get());
        return true;
    }

    @Override
    public ReadUsuarioDto getDoador(Integer idUsuario) {
        Optional<Doador> doador = _repository.findByCod(idUsuario);
        if (doador.isEmpty())
            return null;

        ReadUsuarioDto doadorDto = _mapper.map(doador.get(), ReadUsuarioDto.class);
        return doadorDto;
    }

    @Override
    public List<ReadUsuarioDto> getDoadorByNome(String nomeDoador) {
        List<Doador> doadores = _repository.findByNomeContains(nomeDoador);

        if (doadores.isEmpty())
            return null;

        List<ReadUsuarioDto> doadoresDto = new ArrayList<>();

        for (Doador doador : doadores) {
            doadoresDto.add(_mapper.map(doador, ReadUsuarioDto.class));
        }
        return doadoresDto;
    }

    @Override
    public boolean atualizarDadosCadastrais(Integer idUsuario, UpdateDoadorDto doadorAtualizado) {
        Optional<Doador> doadorAtualOpt = _repository.findByCod(idUsuario);
        if (doadorAtualOpt.isEmpty())
            return false;

        Doador doadorAtual = doadorAtualOpt.get();
        _mapper.map(doadorAtualizado, doadorAtual);
        _repository.save(doadorAtual);
        return true;
    }
}