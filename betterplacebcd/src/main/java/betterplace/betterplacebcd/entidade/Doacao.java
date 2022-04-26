package betterplace.betterplacebcd.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
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
    private Integer fkVakinha;

    public Integer getFkVakinha() {
        return fkVakinha;
    }

    public void setFkVakinha(Integer fkVakinha) {
        this.fkVakinha = fkVakinha;
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
