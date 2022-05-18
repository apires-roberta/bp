package entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
public class Doacao {
    @NotNull @Positive
    private Integer fkDoador;

    @NotNull @Positive
    private Integer fkOng;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoacao;

    @NotNull @Positive
    private Double valorDoacao;

    @PastOrPresent @NotNull
    private LocalDateTime dataDoacao;

    @NotNull @Positive
    private Integer fkCampanha;

    public Integer getFkCampanha() {
        return fkCampanha;
    }

    public void setFkCampanha(Integer fkCampanha) {
        this.fkCampanha = fkCampanha;
    }

    public Integer getFkDoador() {
        return fkDoador;
    }

    public void setFkDoador(Integer fkDoador) {
        this.fkDoador = fkDoador;
    }

    public Integer getFkOng() {
        return fkOng;
    }

    public void setFkOng(Integer fkOng) {
        this.fkOng = fkOng;
    }

    public Integer getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(Integer idDoacao) {
        this.idDoacao = idDoacao;
    }

    public Double getValorDoacao() {
        return valorDoacao;
    }

    public void setValorDoacao(Double valorDoacao) {
        this.valorDoacao = valorDoacao;
    }

    public LocalDateTime getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(LocalDateTime dataDoacao) {
        this.dataDoacao = dataDoacao;
    }
}
