package betterplace.betterplacebcd.entidade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Doacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoacao;

    @ManyToOne
    private Campanha campanha;

    @ManyToOne
    private Doador doador;

    @NotNull @Positive
    private Double valorDoacao;

    @PastOrPresent @NotNull
    private LocalDateTime dataDoacao;

    public Doacao() {
        this.dataDoacao = LocalDateTime.now();
    }

    public Doacao(Campanha campanha, Doador doador, Double valorDoacao) {
        this.campanha = campanha;
        this.doador = doador;
        this.valorDoacao = valorDoacao;
        this.dataDoacao = LocalDateTime.now();
    }

    public Doador getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
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
