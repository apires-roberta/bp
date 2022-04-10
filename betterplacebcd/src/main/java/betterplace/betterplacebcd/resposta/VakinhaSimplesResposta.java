package betterplace.betterplacebcd.resposta;

public class VakinhaSimplesResposta {

    private String nomeVakinha;
    private String nomeItem;
    private String descItem;
    private Double valorMeta;
    private Double valorArrecadado;

    public VakinhaSimplesResposta(String nomeVakinha, String nomeItem, String descItem, Double valorMeta, Double valorArrecadado) {
        this.nomeVakinha = nomeVakinha;
        this.nomeItem = nomeItem;
        this.descItem = descItem;
        this.valorMeta = valorMeta;
        this.valorArrecadado = valorArrecadado;
    }

    public String getNomeVakinha() {
        return nomeVakinha;
    }

    public void setNomeVakinha(String nomeVakinha) {
        this.nomeVakinha = nomeVakinha;
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

    public Double getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(Double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public Double getValorArrecadado() {
        return valorArrecadado;
    }

    public void setValorArrecadado(Double valorArrecadado) {
        this.valorArrecadado = valorArrecadado;
    }
}
