package api.feed.apifeed.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Feed {

    @Id
    private Long codigo;

    private String nome;

    @Column(length = 50_000_000)
    private byte[] fotoPerfil;

    private LocalDateTime dataPublicacao;

    private String descricao;

    @Column(length = 50_000_000)
    private byte[] fotoFeed;

    public Feed(Long codigo, LocalDateTime dataPublicacao, String descricao) {
        this.codigo = codigo;
        this.dataPublicacao = dataPublicacao;
        this.descricao = descricao;
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

    public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public byte[] getFotoFeed() {
        return fotoFeed;
    }

    public void setFotoFeed(byte[] fotoFeed) {
        this.fotoFeed = fotoFeed;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
