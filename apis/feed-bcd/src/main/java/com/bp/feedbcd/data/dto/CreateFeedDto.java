package com.bp.feedbcd.data.dto;

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

    public void setOng(OngOnlyCodDto ong) {
        Ong = ong;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}