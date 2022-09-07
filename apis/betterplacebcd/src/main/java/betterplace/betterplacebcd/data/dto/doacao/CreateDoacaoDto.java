package betterplace.betterplacebcd.data.dto.doacao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreateDoacaoDto {
    @NotNull @Positive
    private Integer idDoador;
    @NotNull @Positive
    private Integer idCampanha;
    @NotNull @Positive
    private Double valorDoacao;

    public Integer getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(Integer idDoador) {
        this.idDoador = idDoador;
    }

    public Integer getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(Integer idCampanha) {
        this.idCampanha = idCampanha;
    }

    public Double getValorDoacao() {
        return valorDoacao;
    }

    public void setValorDoacao(Double valorDoacao) {
        this.valorDoacao = valorDoacao;
    }
}
