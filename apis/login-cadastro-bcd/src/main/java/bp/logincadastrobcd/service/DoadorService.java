package bp.logincadastrobcd.service;

import bp.logincadastrobcd.dto.doador.CreateDoador;
import bp.logincadastrobcd.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.dto.usuario.ReadUsuarioDto;
import bp.logincadastrobcd.entidade.Doador;
import bp.logincadastrobcd.repositorio.DoadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Service
@CrossOrigin
public class DoadorService implements IDoadorService{
    @Autowired
    private DoadorRepository repository;
    @Autowired
    private ModelMapper _mapper;
    @Override
    public Integer login(LoginUsuarioDto usuarioLogin) {
        if (!repository.existsByEmailAndSenha(usuarioLogin.getEmail(), usuarioLogin.getSenha()))
            return null;

        Doador doador = repository.findByEmail(usuarioLogin.getEmail());
        doador.setAutenticado(true);
        repository.save(doador);
        return doador.getCod();
    }
    @Override
    public String cadastro(CreateDoador novoDoador) {

        if (repository.existsByEmail(novoDoador.getEmail()))
            return "Existente"; //409 - Conflict

        if (novoDoador.getCpf() == null || novoDoador.getEmail() == null || novoDoador.getSenha() == null)
            return "Bad Request";

        Doador doadorMap = _mapper.map(novoDoador, Doador.class);
        repository.save(doadorMap);

        return doadorMap.getCod().toString();
    }
    @Override
    public boolean atualizarFotoDoador(Integer idDoador, byte[] fotoPerfil) {
        if (!repository.existsById(idDoador))
            return false;

        Optional<Doador> doador = repository.findByCod(idDoador);
        if (doador.isEmpty())
            return false;

        doador.get().setFotoPerfil(fotoPerfil);
        repository.save(doador.get());
        return true;
    }
    @Override
    public boolean logoff(Integer idUsuario) {
        if (!repository.existsByCodAndAutenticadoTrue(idUsuario))
            return false;

        Optional<Doador> doador = repository.findByCod(idUsuario);
        if (doador.isEmpty())
            return false;

        doador.get().setAutenticado(false);
        repository.save(doador.get());
        return true;
    }
    @Override
    public boolean deletarConta(Integer idUsuario) {
        Optional<Doador> doador = repository.findByCod(idUsuario);
        if (doador.isEmpty())
            return false;

        repository.delete(doador.get());
        return true;
    }
    @Override
    public ReadUsuarioDto getDoador(Integer idUsuario) {
        Optional<Doador> doador = repository.findByCod(idUsuario);
        if (doador.isEmpty())
            return null;

        ReadUsuarioDto doadorDto = _mapper.map(doador.get(), ReadUsuarioDto.class);
        return doadorDto;
    }
}