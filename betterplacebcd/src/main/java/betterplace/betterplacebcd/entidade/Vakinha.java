package betterplace.betterplacebcd.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Vakinha {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVakinha;

    @NotNull @NotBlank @Size(min = 2, max = 45)
    private String nomeVakinha;

    @NotNull @NotBlank @Size(min = 2, max = 25)
    private String nomeItem;

    @NotNull @NotBlank @Size(min = 2, max = 100)
    private String descVakinha;

    @NotNull @Positive
    private Double valorNecessario;

    @PastOrPresent @NotNull
    private LocalDateTime dataCriacao;

    @NotNull @Positive
    private Integer fkOng;

    public String getNomeItem() { return nomeItem; }

    public void setNomeItem(String nomeItem) { this.nomeItem = nomeItem; }

    public String getDescVakinha() {
        return descVakinha;
    }

    public void setDescVakinha(String descVakinha) {
        this.descVakinha = descVakinha;
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
