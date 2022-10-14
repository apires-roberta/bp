package com.bp.feedbcd.servicesreferences;

import com.bp.feedbcd.data.dto.ong.ReadOngDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Service
@FeignClient(name = "ongService", url = "localhost:8080/bp/ong/")
public interface IOngService {
    @GetMapping("{idOng}")
    ReadOngDto getOngById(@PathVariable Integer idOng);
}