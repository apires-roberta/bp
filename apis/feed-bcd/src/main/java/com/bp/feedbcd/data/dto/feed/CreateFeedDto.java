package com.bp.feedbcd.data.dto.feed;

import javax.persistence.Column;
import java.net.URL;

public class CreateFeedDto {
    private Integer idOng;
    private String descricao;
    @Column(length = 50_000_000)
    private URL fotoFeed;

    public Integer getIdOng() {
        return idOng;
    }

    public void setIdOng(Integer idOng) {
        this.idOng = idOng;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public URL getFotoFeed() {
        return fotoFeed;
    }

    public void setFotoFeed(URL fotoFeed) {
        this.fotoFeed = fotoFeed;
    }
}