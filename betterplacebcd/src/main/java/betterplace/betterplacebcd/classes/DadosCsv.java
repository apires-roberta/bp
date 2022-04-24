package betterplace.betterplacebcd.classes;

public class DadosCsv {
    private String nomeVakinha, itemVakinha, descVakinha, dataCriacao, dataDoacao, nomeDoador, dataPrevisao;
    private Double valorNecessario, valorAtual, valorDoacao, media;
    private Integer qtdDoacoes;

    public DadosCsv(Object[] objetos) {
        nomeVakinha = objetos[0].toString();
        itemVakinha = objetos[1].toString();
        descVakinha = objetos[2].toString();
        dataCriacao = objetos[3].toString();
        dataDoacao = objetos[7].toString();
        nomeDoador = objetos[9].toString();
        dataPrevisao = objetos[11].toString();
        valorNecessario = Double.parseDouble(objetos[4].toString());
        valorAtual =  Double.parseDouble(objetos[6].toString());
        valorDoacao =  Double.parseDouble(objetos[8].toString());
        media =  Double.parseDouble(objetos[10].toString());
        qtdDoacoes = Integer.parseInt(objetos[5].toString());
    }

    public String getNomeVakinha() {
        return nomeVakinha;
    }

    public void setNomeVakinha(String nomeVakinha) {
        this.nomeVakinha = nomeVakinha;
    }

    public String getItemVakinha() {
        return itemVakinha;
    }

    public void setItemVakinha(String itemVakinha) {
        this.itemVakinha = itemVakinha;
    }

    public String getDescVakinha() {
        return descVakinha;
    }

    public void setDescVakinha(String descVakinha) {
        this.descVakinha = descVakinha;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(String dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public String getNomeDoador() {
        return nomeDoador;
    }

    public void setNomeDoador(String nomeDoador) {
        this.nomeDoador = nomeDoador;
    }

    public String getDataPrevisao() {
        return dataPrevisao;
    }

    public void setDataPrevisao(String dataPrevisao) {
        this.dataPrevisao = dataPrevisao;
    }

    public Double getValorNecessario() {
        return valorNecessario;
    }

    public void setValorNecessario(Double valorNecessario) {
        this.valorNecessario = valorNecessario;
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public Double getValorDoacao() {
        return valorDoacao;
    }

    public void setValorDoacao(Double valorDoacao) {
        this.valorDoacao = valorDoacao;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Integer getQtdDoacoes() {
        return qtdDoacoes;
    }

    public void setQtdDoacoes(Integer qtdDoacoes) {
        this.qtdDoacoes = qtdDoacoes;
    }
}
