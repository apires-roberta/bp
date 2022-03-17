package BetterPlace.BetterPlaceBE.Controller;


import BetterPlace.BetterPlaceBE.Classes.UsuarioCli;
import BetterPlace.BetterPlaceBE.Classes.UsuarioOng;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ONG")
public class UsuarioOngController {
    private UsuarioOng usuarioOng = new UsuarioOng();

    @PostMapping("/cadastro")
    public String cadastrarCliente(@RequestBody() UsuarioOng usu){
        usu.setLogado(false);
        return usuarioOng.cadastrarONG(usu);
    }

    @GetMapping("/login/{usuario}/{senha}")
    public String autenticar(@PathVariable String usuario, @PathVariable String senha){
        return usuarioOng.autenticar(usuario,senha);
    }
    @GetMapping("/logoff/{usuario}")
    public String desautenticar(@PathVariable String usuario){
        return usuarioOng.desautenticar(usuario);
    }

    @GetMapping("/mostrar")
    public String mostrar(){
        return usuarioOng.mostrarGeral();
    }

}
