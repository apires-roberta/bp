package com.bp.feedbcd.data.dto.feed;

import com.bp.feedbcd.data.dto.ong.ReadOngDto;
import com.bp.feedbcd.entidade.Ong;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ReadFeedDto {
    private Long codigo;
    private ReadOngDto ong;
    private LocalDateTime dataPublicacao;
    @NotNull
    @NotBlank
    private String descricao;
    @Column(length = 50_000_000)
    private byte[] fotoFeed;
    @Column(length = 50_000_000)
    private byte[] fotoPerfilOng;

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

    public byte[] getFotoFeed() {
        return fotoFeed;
    }

    public void setFotoFeed(byte[] fotoFeed) {
        this.fotoFeed = fotoFeed;
    }

    public byte[] getFotoPerfilOng() {
        return fotoPerfilOng;
    }

    public void setFotoPerfilOng(byte[] fotoPerfilOng) {
        this.fotoPerfilOng = fotoPerfilOng;
    }
}