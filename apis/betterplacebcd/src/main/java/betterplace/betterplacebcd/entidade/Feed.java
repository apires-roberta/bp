package betterplace.betterplacebcd.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Feed {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    //@ManyToOne
    private Integer fkOng;
    private LocalDateTime dataPublicacao;
    private String descricao;
    @Column(length = 50_000_000)
    private byte[] fotoFeed;
    @Column(length = 50_000_000)
    private byte[] fotoPerfilOng;

    public Feed(Integer fkOng, LocalDateTime dataPublicacao, String descricao) {
        this.fkOng = fkOng;
        this.dataPublicacao = dataPublicacao;
        this.descricao = descricao;
    }

    public Feed() {
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
    public byte[] getFotoFeed() {
        return fotoFeed;
    }

    public void setFotoFeed(byte[] fotoFeed) {
        this.fotoFeed = fotoFeed;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getFkOng() {
        return fkOng;
    }

    public void setFkOng(Integer fkOng) {
        this.fkOng = fkOng;
    }

    public byte[] getFotoPerfilOng() {
        return fotoPerfilOng;
    }

    public void setFotoPerfilOng(byte[] fotoPerfilOng) {
        this.fotoPerfilOng = fotoPerfilOng;
    }
}
