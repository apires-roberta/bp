package betterplace.betterplacebcd.entidade;

import betterplace.betterplacebcd.data.dto.campanha.CreateCampanhaDto;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Campanha {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCampanha;

    @ManyToOne
    private Ong ong;

    @NotNull @NotBlank @Size(min = 2, max = 45)
    private String nomeCampanha;

    @NotNull @NotBlank @Size(min = 2, max = 25)
    private String nomeItem;

    @NotNull @NotBlank
    private String tipoCampanha;

    @NotNull @NotBlank @Size(min = 2, max = 100)
    private String descCampanha;

    @NotNull @Positive
    private Double valorNecessario;

    @PastOrPresent @NotNull
    private LocalDateTime dataCriacao;

    public Campanha() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Campanha(CreateCampanhaDto novaCampanha, Ong ong) {
        this.nomeCampanha = novaCampanha.getNomeCampanha();
        this.nomeItem = novaCampanha.getNomeItem();
        this.tipoCampanha = novaCampanha.getTipoCampanha();
        this.descCampanha = novaCampanha.getDescCampanha();
        this.valorNecessario = novaCampanha.getValorNecessario();
        this.dataCriacao = LocalDateTime.now();
        this.ong = ong;
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

    public String getNomeItem() { return nomeItem; }

    public void setNomeItem(String nomeItem) { this.nomeItem = nomeItem; }

    public String getDescCampanha() {
        return descCampanha;
    }

    public void setDescCampanha(String descCampanha) {
        this.descCampanha = descCampanha;
    }

    public Integer getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(Integer idCampanha) {
        this.idCampanha = idCampanha;
    }

    public String getNomeCampanha() {
        return nomeCampanha;
    }

    public void setNomeCampanha(String nomeCampanha) {
        this.nomeCampanha = nomeCampanha;
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

    public String getTipoCampanha() {
        return tipoCampanha;
    }

    public void setTipoCampanha(String tipoCampanha) {
        this.tipoCampanha = tipoCampanha;
    }
}
