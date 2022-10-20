package com.bp.feedbcd.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.net.URL;
import java.time.LocalDateTime;

@Entity
public class Feed {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Ong ong;
    private LocalDateTime dataPublicacao;
    @NotNull @NotBlank
    private String descricao;
    @Column(length = 50_000_000)
    private URL fotoFeed;

    public Feed(Ong ong, String descricao, URL fotoFeed) {
        this.ong = ong;
        this.dataPublicacao = LocalDateTime.now();
        this.descricao = descricao;
        this.fotoFeed = fotoFeed;
    }

    public Feed() {
        this.dataPublicacao = LocalDateTime.now();
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public URL getFotoFeed() {
        return fotoFeed;
    }

    public void setFotoFeed(URL fotoFeed) {
        this.fotoFeed = fotoFeed;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Ong getOng() {
        return ong;
    }

    public void setFkOng(Ong ong) {
        this.ong = ong;
    }
}
