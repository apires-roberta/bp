package betterplace.betterplacebcd.data.dto.campanha;

import betterplace.betterplacebcd.data.enums.TipoCampanha;
import betterplace.betterplacebcd.data.enums.TipoCampanhaConverter;

import java.time.LocalDateTime;

public class ReadCampanhaDto {
    private Integer idCampanha;
    private Integer ongCod;
    private String nomeCampanha;
    private String nomeItem;
    private TipoCampanha tipoCampanha;
    private String descCampanha;
    private Double valorNecessario;
    private LocalDateTime dataCriacao;
    private Double totalDoado;

    public Integer getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(Integer idCampanha) {
        this.idCampanha = idCampanha;
    }

    public Integer getOngCod() {
        return ongCod;
    }

    public void setOngCod(Integer ongCod) {
        this.ongCod = ongCod;
    }

    public String getNomeCampanha() {
        return nomeCampanha;
    }

    public void setNomeCampanha(String nomeCampanha) {
        this.nomeCampanha = nomeCampanha;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public TipoCampanha getTipoCampanha() {
        return tipoCampanha;
    }

    public void setTipoCampanha(TipoCampanha tipoCampanha) {
        this.tipoCampanha = tipoCampanha;
    }

    public String getDescCampanha() {
        return descCampanha;
    }

    public void setDescCampanha(String descCampanha) {
        this.descCampanha = descCampanha;
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

    public Double getTotalDoado() {
        return totalDoado;
    }

    public void setTotalDoado(Double totalDoado) {
        this.totalDoado = totalDoado;
    }
}