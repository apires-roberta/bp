package com.example.ApiCsv.entidade;

import net.sourceforge.openforecast.*;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Forecast {
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public LocalDate gerarForecast(List<Doacao> doacoes, LocalDate diaCriacao, Double valorNecessario, Double valorAtual){
        List<dataValor> listaValores = new ArrayList();
        Double valorDia = 0.0;
        String dia = doacoes.get(0).getDataDoacao().format(formato);
        DataSet observedData = new DataSet();
        DataPoint dp;
        Integer qtdeDiasRestante=0;
        int contador =0;
        for(Doacao doacao : doacoes){
            if(doacao.getDataDoacao().format(formato).equalsIgnoreCase(dia)){
                valorDia+=doacao.getValorDoacao();
            }
            else {
                contador++;
                listaValores.add(new dataValor(valorDia, LocalDate.parse(dia, formato).atStartOfDay()));
                valorDia = null;
                dia = doacao.getDataDoacao().format(formato);
            }
        }
        if(contador==0){
            listaValores.add(new dataValor(valorDia, LocalDate.parse(dia, formato).atStartOfDay()));
        }
        Double[] vetorDoacoes = new Double[diferencaEntreDataCriacaoEDiaAtual(LocalDate.now(), diaCriacao)];

        for(int i = 0; i<vetorDoacoes.length;i++){
            Integer posicao = verificaDia(diaCriacao.plusDays(i), listaValores);
            if(posicao>=0){
                vetorDoacoes[i] = listaValores.get(posicao).getSoma();
            }
            else{
                vetorDoacoes[i] = 0.0;
            }
        }

        for(int qtdeDias=1; qtdeDias<=vetorDoacoes.length; qtdeDias++){
            dp = new Observation( vetorDoacoes[qtdeDias-1] );
            dp.setIndependentValue( "x", qtdeDias );
            observedData.add( dp );
        }
        ForecastingModel forecaster = Forecaster.getBestForecast( observedData );
        int i = vetorDoacoes.length+1;
        while(valorAtual<=valorNecessario){
            DataSet requiredDataPoints = new DataSet();
            dp = new Observation( 0.0 );
            dp.setIndependentValue( "x", i );
            requiredDataPoints.add( dp );
            forecaster.forecast( requiredDataPoints );
            Object[] vetorForecast = requiredDataPoints.toArray();
            for(Object previsao : vetorForecast) {
                String[] valor = previsao.toString().replace("(x=", "")
                        .replace("dependentValue=", "").replace(")", "").split(",");
                valorAtual+=Double.parseDouble(valor[1]);
                qtdeDiasRestante = (int) Double.parseDouble(valor[0]);
            }
            i++;
        }
        return LocalDate.now().plusDays(qtdeDiasRestante);
    }

    public Integer diferencaEntreDataCriacaoEDiaAtual(LocalDate dia1, LocalDate dia2){
        Period periodo = Period.between(dia2, dia1);
        return periodo.getDays()+(periodo.getMonths()*30)+(periodo.getYears()*365);
    }

    public Integer verificaDia(LocalDate dia, List<dataValor> lista){
        for (int i=0;i< lista.size();i++){
            if(dia.equals(lista.get(i).getDia().toLocalDate())){
                return i;
            }
        }
        return -1;
    }

    public void teste(){
        System.out.println("oi");
    }
}
