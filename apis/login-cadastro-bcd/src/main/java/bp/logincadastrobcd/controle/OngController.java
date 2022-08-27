package bp.logincadastrobcd.controle;

import bp.logincadastrobcd.dto.ong.CreateOng;
import bp.logincadastrobcd.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.dto.usuario.ReadUsuarioDto;
import bp.logincadastrobcd.service.IOngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.*;

@Service
@RestController
@CrossOrigin
@RequestMapping("/bp/ong")
public class OngController {
    @Autowired
    private IOngService _ongService;

    @PostMapping("/login")
    public ResponseEntity<Integer> login(@RequestBody @Valid LoginUsuarioDto usuarioLogin) {
        if (usuarioLogin == null)
            return status(400).build();

        Integer codUsuario = _ongService.login(usuarioLogin);
        return codUsuario != null ? status(200).body(codUsuario) : status(404).build();
    }
    @PostMapping("/cadastroOng")
    public ResponseEntity<String> cadastro(@RequestBody @Valid CreateOng novoUsuario) {
        if (novoUsuario == null)
            return status(400).build();

        String resultado = _ongService.cadastro(novoUsuario);

        if (resultado.equalsIgnoreCase("Existente"))
            return status(409).build();

        if (resultado.equalsIgnoreCase("Bad Request"))
            return status(400).build();

        return status(201).body(resultado);
    }
    @PatchMapping(value = "/{idUsuario}", consumes = "image/jpeg")
    public ResponseEntity atualizarFotoOng(@PathVariable Integer idUsuario, @RequestBody byte[] fotoPerfil){
        if (idUsuario == null || fotoPerfil == null)
            return status(400).build();

        boolean atualizado = _ongService.atualizarFotoOng(idUsuario, fotoPerfil);

        return atualizado ? status(200).build() : status(404).build();
    }

    @DeleteMapping("/logoff/{idUsuario}")
    public ResponseEntity logoff(@PathVariable Integer idUsuario) {
        if (idUsuario == null)
            return status(404).build();

        boolean deslogado = _ongService.logoff(idUsuario);
        return deslogado ? status(200).build() : status(404).build();
    }
    @DeleteMapping("/deletarConta/{idUsuario}")
    public ResponseEntity deletarConta(@PathVariable Integer idUsuario) {
        if (idUsuario == null)
            return status(404).build();

        boolean deletado = _ongService.deletarConta(idUsuario);
        return deletado ? status(200).build() : status(404).build();
    }
    @GetMapping("/{idUsuario}")
    public ResponseEntity<ReadUsuarioDto> getOng(@PathVariable Integer idUsuario) {
        if (idUsuario == null)
            return status(404).build();

        ReadUsuarioDto doador = _ongService.getOng(idUsuario);
        return doador != null ? status(200).body(doador) : status(404).build();
    }
}
