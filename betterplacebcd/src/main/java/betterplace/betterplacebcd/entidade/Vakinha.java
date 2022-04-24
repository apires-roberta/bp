package betterplace.betterplacebcd.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Vakinha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVakinha;
    private String nomeVakinha;
    private String nomeItem;
    private String DescVakinha;
    private Double valorNecessario;
    private LocalDateTime dataCriacao;
    private Integer fkOng;

    public String getNomeItem() { return nomeItem; }

    public void setNomeItem(String nomeItem) { this.nomeItem = nomeItem; }

    public String getDescVakinha() {
        return DescVakinha;
    }

    public void setDescVakinha(String descVakinha) {
        DescVakinha = descVakinha;
    }

    public Integer getFkOng() {
        return fkOng;
    }

    public void setFkOng(Integer fkOng) {
        this.fkOng = fkOng;
    }

    public Integer getIdVakinha() {
        return idVakinha;
    }

    public void setIdVakinha(Integer idVakinha) {
        this.idVakinha = idVakinha;
    }

    public String getNomeVakinha() {
        return nomeVakinha;
    }

    public void setNomeVakinha(String nomeVakinha) {
        this.nomeVakinha = nomeVakinha;
    }

    public Double getValorNecessario() {
        return valorNecessario;
    }

    public void setValorNecessario(Double valorNecessario) {
        this.valorNecessario = valorNecessario;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
