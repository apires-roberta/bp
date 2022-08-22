package betterplace.betterplacebcd.servicesreferences;

import betterplace.betterplacebcd.data.dto.usuario.ReadUsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doadorService", url = "localhost:8081/bp/doador/")
public interface IDoadorService {
    @GetMapping("{idUsuario}")
    ReadUsuarioDto getUsuarioById(@PathVariable Integer idUsuario);
}