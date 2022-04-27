package betterplace.betterplacebcd.classes;

import java.time.LocalDate;
import java.time.Period;

public class Media {
    private Integer dias;

    public Media(LocalDate dataCriacao) {
        Period periodo = Period.between(dataCriacao, LocalDate.now());
        dias = periodo.getDays()+(periodo.getMonths()*30)+(periodo.getYears()*365);
    }

    public Double calcularMedia(Double valorArrecadado){
        return valorArrecadado / dias;
    }

    public LocalDate previsaoMedia(Double valorArrecadado, Double valorNecessario){
        Double media = calcularMedia(valorArrecadado);
        Integer qtdeDias=0;
        for(int i = 0; valorArrecadado<=valorNecessario; i++){
            valorArrecadado+=media;
            qtdeDias=i;
        }
        return LocalDate.now().plusDays(qtdeDias);
    }
}
