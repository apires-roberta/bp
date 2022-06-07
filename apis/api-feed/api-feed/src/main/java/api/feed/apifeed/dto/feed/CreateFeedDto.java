package api.feed.apifeed.dto.feed;
import api.feed.apifeed.dto.ong.OngNewFeedDto;
import api.feed.apifeed.entidade.Ong;

import java.time.LocalDateTime;

public class CreateFeedDto {
    private Integer fkOng;
    private String descricao;

    private LocalDateTime dataPublicacao;

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public Integer getFkOng() {
        return fkOng;
    }

    public void setFkOng(Integer fkOng) {
        this.fkOng = fkOng;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}