package betterplace.betterplacebcd.entidade;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // do pacote javax.persistence
public class Vakinha {
    @Id  // do pacote javax.persistence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    private String nomeVakinha;

    private Double valorMeta;

    private String nomeItem;

    private String descItem;

    private Double valorArrecadado;

    private Integer fkOng;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNomeVakinha() {
        return nomeVakinha;
    }

    public void setNomeVakinha(String nomeVakinha) {
        this.nomeVakinha = nomeVakinha;
    }

    public Double getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(Double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getDescItem() {
        return descItem;
    }

    public void setDescItem(String descItem) {
        this.descItem = descItem;
    }

    public Double getValorArrecadado() {
        return valorArrecadado;
    }

    public void setValorArrecadado(Double valorArrecadado) {
        this.valorArrecadado = valorArrecadado;
    }

    public Integer getFkOng() {
        return fkOng;
    }

    public void setFkOng(Integer fkOng) {
        this.fkOng = fkOng;
    }
}
