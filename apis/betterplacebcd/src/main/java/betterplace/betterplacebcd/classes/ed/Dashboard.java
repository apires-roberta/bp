package betterplace.betterplacebcd.classes.ed;

import java.util.List;

public class Dashboard {
    private Integer idCampanha;
    private List<Object> doacoesDia;
    private Object doacoesSemana;
    private Object doacoesValor;

    public Dashboard(Integer idCampanha, List<Object> doacoesDia, Object doacoesSemana, Object doacoesValor) {
        this.idCampanha = idCampanha;
        this.doacoesDia = doacoesDia;
        this.doacoesSemana = doacoesSemana;
        this.doacoesValor = doacoesValor;
    }

    public Integer getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(Integer idCampanha) {
        this.idCampanha = idCampanha;
    }

    public List<Object> getDoacoesDia() {
        return doacoesDia;
    }

    public void setDoacoesDia(List<Object> doacoesDia) {
        this.doacoesDia = doacoesDia;
    }

    public Object getDoacoesSemana() {
        return doacoesSemana;
    }

    public void setDoacoesSemana(Object doacoesSemana) {
        this.doacoesSemana = doacoesSemana;
    }

    public Object getDoacoesValor() {
        return doacoesValor;
    }

    public void setDoacoesValor(Object doacoesValor) {
        this.doacoesValor = doacoesValor;
    }
}
