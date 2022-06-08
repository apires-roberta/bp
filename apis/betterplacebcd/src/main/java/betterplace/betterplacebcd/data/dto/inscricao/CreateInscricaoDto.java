package betterplace.betterplacebcd.data.dto.inscricao;

public class CreateInscricaoDto {
    private Integer fkOng;
    private Integer fkDoador;

    public Integer getFkOng() {
        return fkOng;
    }

    public Integer getFkDoador() {
        return fkDoador;
    }

    public void setFkOng(Integer fkOng) {
        this.fkOng = fkOng;
    }

    public void setFkDoador(Integer fkDoador) {
        this.fkDoador = fkDoador;
    }
}
