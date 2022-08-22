package com.bp.feedbcd.data.dto.feed;
public class CreateFeedDto {
    private Integer idOng;
    private String descricao;

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
}