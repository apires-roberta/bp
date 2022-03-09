package BetterPlace.BetterPlaceBE.Controller;

import BetterPlace.BetterPlaceBE.Classes.UsuarioCli;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class UsuarioClienteController {
    private UsuarioCli usuarioCli = new UsuarioCli();

    @PostMapping("/cadastro")
    public String cadastrarCliente(@RequestBody() UsuarioCli usu){
        usu.setLogado(false);
        return usuarioCli.cadastrarCli(usu);
    }


    @GetMapping("/login/{usuario}/{senha}")
    public String autenticar(@PathVariable String usuario, @PathVariable String senha){
        return usuarioCli.autenticar(usuario, senha);
    }

    @GetMapping("/logoff/{usuario}")
    public String desautenticar(@PathVariable String usuario){
       return usuarioCli.desautenticar(usuario);
    }

    @GetMapping("/mostrar")
    public String mostrar(){
        return usuarioCli.mostrarGeral();
    }
}
