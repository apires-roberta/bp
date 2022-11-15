package betterplace.betterplacebcd.servicesreferences;

import betterplace.betterplacebcd.data.dto.usuario.ReadUsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ongService", url = "${loginCadastro}/ong/")
public interface IOngService {
    @GetMapping("{idOng}")
    ReadUsuarioDto getOngById(@PathVariable Integer idOng);
}