package bp.logincadastrobcd.service;

import bp.logincadastrobcd.dto.doador.CreateDoador;
import bp.logincadastrobcd.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.entidade.Doador;
import bp.logincadastrobcd.repositorio.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@Service
public class DoadorService implements IDoadorService{
    @Autowired
    private DoadorRepository repository;
    @Override
    public Integer login(LoginUsuarioDto usuarioLogin) {
        if (!repository.existsByEmailAndSenha(usuarioLogin.getEmail(), usuarioLogin.getSenha()))
            return null;

        Doador doador = repository.findByEmail(usuarioLogin.getEmail());
        doador.setAutenticado(true);
        repository.save(doador);
        return doador.getCod().toString();
    }
    @Override
    public String cadastro(CreateDoador novoDoador) {

        if (repository.existsByEmail(novoDoador.getEmail()))
            return "Existente"; //409 - Conflict

        if (novoDoador.getCpf() == null || novoDoador.getEmail() == null || novoDoador.getSenha() == null)
            return "Bad Request";

        Doador doador = new Doador(novoDoador.getNome(), novoDoador.getEmail(), novoDoador.getSenha(),
                novoDoador.getUsuario(), novoDoador.getTelefone(), novoDoador.getCpf());
        repository.save(doador);

        return doador.getCod().toString();
    }
    @Override
    public boolean atualizarFotoDoador(Integer idDoador, byte[] fotoPerfil) {
        if (!repository.existsById(idDoador))
            return false;

        Optional<Doador> doador = repository.findByCod(idDoador);
        doador.get().setFotoPerfil(fotoPerfil);
        repository.save(doador.get());
        return true;
    }
    @Override
    public boolean logoff(Integer idUsuario) {
        if (!repository.existsByCodAndAutenticadoTrue(idUsuario))
            return false;

        Optional<Doador> doador = repository.findByCod(idUsuario);
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
    public Doador getDoador(Integer idUsuario) {
        Optional<Doador> doador = repository.findByCod(idUsuario);
        if (doador.isEmpty())
            return null;

        return doador.get();
    }
}