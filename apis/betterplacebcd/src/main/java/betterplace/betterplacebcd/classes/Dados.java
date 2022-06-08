package betterplace.betterplacebcd.classes;

import java.time.LocalDate;

public class Dados {
    private String nomeCampanha, itemCampanha, descCampanha, nomeDoador;
    private Double valorNecessario, valorAtual, valorDoacao, media;
    private Integer qtdDoacoes;
    private LocalDate dataCriacao, dataDoacao, dataPrevisao;

    public Dados(String nomeCampanha, String itemCampanha, String descCampanha, LocalDate dataCriacao, LocalDate dataDoacao,
                 String nomeDoador, LocalDate dataPrevisao, Double valorNecessario, Double valorAtual, Double valorDoacao,
                 Double media, Integer qtdDoacoes) {
        this.nomeCampanha = nomeCampanha;
        this.itemCampanha = itemCampanha;
        this.descCampanha = descCampanha;
        this.dataCriacao = dataCriacao;
        this.dataDoacao = dataDoacao;
        this.nomeDoador = nomeDoador;
        this.dataPrevisao = dataPrevisao;
        this.valorNecessario = valorNecessario;
        this.valorAtual = valorAtual;
        this.valorDoacao = valorDoacao;
        this.media = media;
        this.qtdDoacoes = qtdDoacoes;
    }

    public String getNomeCampanha() {
        return nomeCampanha;
    }

    public void setNomeCampanha(String nomeCampanha) {
        this.nomeCampanha = nomeCampanha;
    }

    public String getItemCampanha() {
        return itemCampanha;
    }

    public void setItemCampanha(String itemCampanha) {
        this.itemCampanha = itemCampanha;
    }

    public String getDescCampanha() {
        return descCampanha;
    }

    public void setDescCampanha(String descCampanha) {
        this.descCampanha = descCampanha;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(LocalDate dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public String getNomeDoador() {
        return nomeDoador;
    }

    public void setNomeDoador(String nomeDoador) {
        this.nomeDoador = nomeDoador;
    }

    public LocalDate getDataPrevisao() {
        return dataPrevisao;
    }

    public void setDataPrevisao(LocalDate dataPrevisao) {
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
