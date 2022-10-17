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
    var vetorDados=props.dados;
    const vetorValor=[]
    const vetorDias=[]
    var titulo;
    if(props.grafico=="Semanal"){
            for(var j=0; j<vetorDados.length; j++){
                vetorValor.push(vetorDados[j]) 
                vetorDias.push("S"+(j+1))
            }
            titulo = "Quantidade doação X Semana"
    }
    else{
        for(var i=0; i<vetorDados.length; i++){
            vetorValor.push(vetorDados[i]) 
            vetorDias.push(i==vetorDados.length-1 ? 'OV':'R$'+(i+1)+'0,00')
        }
        titulo = "Quantidade doação X Valores"
    }
    
  configGrafico(vetorValor, vetorDias, props.cor, titulo);
  return (
  <>
  <Bar data={data} options={options}/>
  </>
  );
}
function configGrafico(v1, v2, cor, titulo){
    const labels=v2
    options= {
        plugins: {
            legend: {
                display: false,
            },
            title:{
                display: true,
                text: titulo,
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