import React from 'react';
import { Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,} from 'chart.js';
import { Bar } from 'react-chartjs-2';
import styled from "styled-components";

ChartJS.register(CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend);
var data;
var options;

export function GraficoBar(props) {
    const vetorValor=[]
    const vetorDias=[]
    if(props.grafico=="Semanal"){
        for (var i=0;i<5;i++){
            vetorValor.push(getRandomInt(0,100));  
            vetorDias.push("S"+(i+1))                          
        }
    }
    else{
        for (var i=0;i<6;i++){
            vetorValor.push(getRandomInt(0,100)); 
            if(i<5) vetorDias.push("R$"+(i+1)+"0,00")
            else vetorDias.push("OV")                       
        }
    }
    
  configGrafico(vetorValor, vetorDias, props.cor);
  return (
  <>
  <Bar data={data} options={options}/>
  </>
  );
}
function configGrafico(v1, v2, cor){
    const labels=v2
    options= {
        scale: {            
            max: 100
        },
        plugins: {
            legend: {
                display: false,
            },
            tooltip:{
                enabled: false,
            },
        },
        maintainAspectRatio: false,
    }
    
    data = {
      labels,
      datasets: [
        {
          data: v1,
          backgroundColor: cor,
        },
      ],
    };
  }
  
  function getRandomInt(min, max) {
      min = Math.ceil(min);
      max = Math.floor(max);
      return Math.floor(Math.random() * (max - min) + min);
    }

/*
    100 - valorDesejado
     x  - valorAtual

     x = (100*valorAtual)/valorDesejado
*/