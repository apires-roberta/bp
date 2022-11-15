package betterplace.betterplacebcd.servicesreferences;

import betterplace.betterplacebcd.data.dto.doador.ReadDoadorDto;
import betterplace.betterplacebcd.data.dto.doador.UpdateDoadorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@FeignClient(name = "doadorService", url = "${loginCadastro}/doador/")
public interface IDoadorService {
    @GetMapping("{idUsuario}")
    ReadDoadorDto getUsuarioById(@PathVariable Integer idUsuario);
    @PatchMapping("novas-infos/{idUsuario}")
    ResponseEntity<?> atualizarDadosCadastrais(@PathVariable @NotNull @Positive Integer idUsuario,
                                               @RequestBody UpdateDoadorDto doadorAtualizado);
}