package bp.logincadastrobcd.controle;

import bp.logincadastrobcd.controle.dto.doador.CreateDoador;
import bp.logincadastrobcd.controle.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.controle.dto.usuario.ReadUsuarioDto;
import bp.logincadastrobcd.service.IDoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.status;

@Service
@RestController
@CrossOrigin
@RequestMapping("/bp/doador")
public class DoadorController {
    @Autowired
    private IDoadorService _doadorService;
    @PostMapping("/login")
    public ResponseEntity<Integer> login(@RequestBody @Valid LoginUsuarioDto usuarioLogin) {
        if (usuarioLogin == null)
            return status(400).build();

        Integer codUsuario = _doadorService.login(usuarioLogin);
        return codUsuario != null ? status(200).body(codUsuario) : status(404).build();
    }
    @PostMapping("/cadastroDoador")
    public ResponseEntity<String> cadastro(@RequestBody @Valid CreateDoador novoUsuario) {
        if (novoUsuario == null)
            return status(400).build();

        String resultado = _doadorService.cadastro(novoUsuario);

        if (resultado.equalsIgnoreCase("Existente"))
            return status(409).build();

        if (resultado.equalsIgnoreCase("Bad Request"))
            return status(400).build();

        return status(201).body(resultado);
    }
    @PatchMapping(value = "/{idUsuario}", consumes = "image/jpeg")
    public ResponseEntity atualizarFotoDoador(@PathVariable Integer idUsuario, @RequestBody byte[] fotoPerfil){
        if (idUsuario == null || fotoPerfil == null)
            return status(400).build();

        boolean atualizado = _doadorService.atualizarFotoDoador(idUsuario, fotoPerfil);

        return atualizado ? status(200).build() : status(404).build();
    }
    @DeleteMapping("/logoff/{idUsuario}")
    public ResponseEntity logoff(@PathVariable Integer idUsuario){
        if (idUsuario == null)
            return status(404).build();

        boolean deslogado = _doadorService.logoff(idUsuario);
        return deslogado ? status(200).build() : status(404).build();
    }
    @DeleteMapping("/deletarConta/{idUsuario}")
    public ResponseEntity deletarConta(@PathVariable Integer idUsuario){
        if (idUsuario == null)
            return status(404).build();

        boolean deletado = _doadorService.deletarConta(idUsuario);
        return deletado ? status(200).build() : status(404).build();
    }
    @GetMapping("/{idUsuario}")
    public ResponseEntity<ReadUsuarioDto> getDoador(@PathVariable Integer idUsuario) {
        if (idUsuario == null)
            return status(404).build();

        ReadUsuarioDto doador = _doadorService.getDoador(idUsuario);
        return doador != null ? status(200).body(doador) : status(404).build();
    }
}