package betterplace.betterplacebcd.data.dto.feed;
//import api.feed.apifeed.dto.ong.OngNewFeedDto;
//import api.feed.apifeed.entidade.Ong;

import betterplace.betterplacebcd.data.dto.ong.OngOnlyCodDto;

import java.time.LocalDateTime;

public class CreateFeedDto {
    private OngOnlyCodDto Ong;
    private String descricao;

    private LocalDateTime dataPublicacao;

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public OngOnlyCodDto getOng() {
        return Ong;
    }
}