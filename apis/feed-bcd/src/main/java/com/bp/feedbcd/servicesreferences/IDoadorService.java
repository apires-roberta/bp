package com.bp.feedbcd.servicesreferences;

import com.bp.feedbcd.data.dto.usuario.ReadUsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "doadorService", url = "${loginCadastro}/doador/")
public interface IDoadorService {
    @GetMapping("{idUsuario}")
    ReadUsuarioDto getUsuarioById(@PathVariable Integer idUsuario);
}