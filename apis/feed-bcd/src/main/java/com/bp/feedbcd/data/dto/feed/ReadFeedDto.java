package com.bp.feedbcd.data.dto.feed;

import com.bp.feedbcd.data.dto.ong.ReadOngDto;
import com.bp.feedbcd.entidade.Ong;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.net.URL;
import java.time.LocalDateTime;

public class ReadFeedDto {
    private Long codigo;
    private ReadOngDto ong;
    private LocalDateTime dataPublicacao;
    @NotNull
    @NotBlank
    private String descricao;
    @Column(length = 50_000_000)
    private URL fotoFeed;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public ReadOngDto getOng() {
        return ong;
    }

    public void setOng(ReadOngDto ong) {
        this.ong = ong;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
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