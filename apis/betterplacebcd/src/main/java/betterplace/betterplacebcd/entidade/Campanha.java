package betterplace.betterplacebcd.entidade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Campanha {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCampanha;

    @NotNull @NotBlank @Size(min = 2, max = 45)
    private String nomeCampanha;

    @NotNull @NotBlank @Size(min = 2, max = 25)
    private String nomeItem;

    @NotNull @NotBlank @Size(min = 2, max = 100)
    private String descCampanha;

    @NotNull @Positive
    private Double valorNecessario;

    @PastOrPresent @NotNull
    private LocalDateTime dataCriacao;

    @ManyToOne
    private Ong ong;

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

}
