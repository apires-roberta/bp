package bp.logincadastrobcd.controle;

import bp.logincadastrobcd.dto.ong.CreateOng;
import bp.logincadastrobcd.dto.ong.ReadOngDto;
import bp.logincadastrobcd.dto.ong.UpdateOngDto;
import bp.logincadastrobcd.dto.usuario.LoginUsuarioDto;
import bp.logincadastrobcd.service.IOngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.util.List;

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
    //    @PatchMapping(value = "/{idUsuario}", consumes = "image/jpeg")
//    public ResponseEntity atualizarFotoOng(@PathVariable Integer idUsuario, @RequestBody byte[] fotoPerfil){
//        if (idUsuario == null || fotoPerfil == null)
//            return status(400).build();
//
//        boolean atualizado = _ongService.atualizarFotoOng(idUsuario, fotoPerfil);
//
//        return atualizado ? status(200).build() : status(404).build();
//    }
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
    public ResponseEntity<ReadOngDto> getOng(@PathVariable Integer idUsuario) {
        if (idUsuario == null)
            return status(404).build();

        ReadOngDto ong = _ongService.getOng(idUsuario);
        return ong != null ? status(200).body(ong) : status(404).build();
    }

    @GetMapping("/nomeOng")
    public ResponseEntity<List<ReadOngDto>> getOngsByNome(@RequestParam String nomeOng){
        List<ReadOngDto> ongs = _ongService.getOngsByNome(nomeOng);

        return ongs == null ? status(404).build() : status(200).body(ongs);
    }

    @PatchMapping("/novas-infos/{idUsuario}")
    public ResponseEntity<?> atualizarDadosCadastrais(@PathVariable @NotNull @Positive Integer idUsuario,
                                                      @RequestBody @Valid UpdateOngDto ongAtualizada){
        try {
            boolean atualizado = _ongService.atualizarDadosCadastrais(idUsuario, ongAtualizada);
            return atualizado ? status(204).build() : status(404).build();
        }catch (Exception ex){
            throw ex;
        }
    }
}