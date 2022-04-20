package com.example.ApiCsv.entidade;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class dataValor {
    private Double soma;
    private LocalDateTime dia;

    public dataValor(Double soma, LocalDateTime dia) {
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

    @Override
    public String toString() {
        return "dataValor{" +
                "soma=" + soma +
                ", dia=" + dia +
                '}';
    }
}
