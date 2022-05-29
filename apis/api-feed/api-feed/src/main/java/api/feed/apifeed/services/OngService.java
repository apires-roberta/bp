package api.feed.apifeed.services;

import api.feed.apifeed.entidade.Ong;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ong", url = "http://localhost:8080/bp/ong")
public interface OngService {
    @GetMapping("/{idOng}")
    Ong getOng(@PathVariable Integer idOng);
}
