package betterplace.betterplacebcd.data.dto.doacao;

import betterplace.betterplacebcd.entidade.Doador;

public class ReadDoacaoDto {
    private Integer idDoacao;

    private Integer campanhaIdCampanha;

    private String CampanhaOngNome;

    private Integer doadorCod;

    private Double valorDoacao;

    private Double pontuacao;

    public Integer getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(Integer idDoacao) {
        this.idDoacao = idDoacao;
    }

    public Integer getCampanhaIdCampanha() {
        return campanhaIdCampanha;
    }

    public void setCampanhaIdCampanha(Integer campanhaIdCampanha) {
        this.campanhaIdCampanha = campanhaIdCampanha;
    }

    public Integer getDoadorCod() {
        return doadorCod;
    }

    public void setDoadorCod(Integer doadorCod) {
        this.doadorCod = doadorCod;
    }

    public Double getValorDoacao() {
        return valorDoacao;
    }

    public void setValorDoacao(Double valorDoacao) {
        this.valorDoacao = valorDoacao;
    }

    public String getCampanhaOngNome() {
        return CampanhaOngNome;
    }

    public void setCampanhaOngNome(String campanhaOngNome) {
        CampanhaOngNome = campanhaOngNome;
    }

    public Double getPontuacao() {
        return 5 + valorDoacao * 0.5;
    }
}