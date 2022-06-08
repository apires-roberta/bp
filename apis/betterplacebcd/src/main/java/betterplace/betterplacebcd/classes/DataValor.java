package betterplace.betterplacebcd.classes;

import java.time.LocalDateTime;

public class DataValor {
    private Double soma;
    private LocalDateTime dia;

    public DataValor(Double soma, LocalDateTime dia) {
        this.soma = soma;
        this.dia = dia;
    }

    public Double getSoma() {
        return soma;
    }

    public void setSoma(Double soma) {
        this.soma = soma;
    }

    public LocalDateTime getDia() {
        return dia;
    }

    public void setDia(LocalDateTime dia) {
        this.dia = dia;
    }

}
