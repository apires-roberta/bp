package betterplace.betterplacebcd.entidade;

import betterplace.betterplacebcd.data.dto.campanha.CreateCampanhaDto;
import betterplace.betterplacebcd.data.enums.TipoCampanha;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Campanha {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCampanha;

    @ManyToOne @OnDelete(action = OnDeleteAction.CASCADE)
    private Ong ong;

    @NotNull @NotBlank @Size(min = 2, max = 45)
    private String nomeCampanha;

    @NotNull @NotBlank @Size(min = 2, max = 25)
    private String nomeItem;

    private TipoCampanha tipoCampanha;

    @NotNull @NotBlank @Size(min = 2, max = 100)
    private String descCampanha;

    @NotNull @Positive
    private Double valorNecessario;

    @PastOrPresent @NotNull
    private LocalDateTime dataCriacao;

    private boolean disponivel = true;


    public Campanha() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Campanha(CreateCampanhaDto novaCampanha, Ong ong) {
        this.nomeCampanha = novaCampanha.getNomeCampanha();
        this.nomeItem = novaCampanha.getNomeItem();
        this.tipoCampanha = TipoCampanha.valueOf(novaCampanha.getTipoCampanha().toUpperCase()); //Converte a String passada para UpperCase e dps para ENUM, se não existir o ENUM vai resultar em erro 400
        this.descCampanha = novaCampanha.getDescCampanha();
        this.valorNecessario = novaCampanha.getValorNecessario();
        this.dataCriacao = LocalDateTime.now();
        this.ong = ong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campanha)) return false;
        Campanha campanha = (Campanha) o;
        return isDisponivel() == campanha.isDisponivel() && Objects.equals(getIdCampanha(), campanha.getIdCampanha()) && Objects.equals(getOng(), campanha.getOng()) && Objects.equals(getNomeCampanha(), campanha.getNomeCampanha()) && Objects.equals(getNomeItem(), campanha.getNomeItem()) && getTipoCampanha() == campanha.getTipoCampanha() && Objects.equals(getDescCampanha(), campanha.getDescCampanha()) && Objects.equals(getValorNecessario(), campanha.getValorNecessario()) && Objects.equals(getDataCriacao(), campanha.getDataCriacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCampanha(), getOng(), getNomeCampanha(), getNomeItem(), getTipoCampanha(), getDescCampanha(), getValorNecessario(), getDataCriacao(), isDisponivel());
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

    public TipoCampanha getTipoCampanha() {
        return tipoCampanha;
    }

    public void setTipoCampanha(TipoCampanha tipoCampanha) {
        this.tipoCampanha = tipoCampanha;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}