import React from 'react';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,} from 'chart.js';
import { Line } from 'react-chartjs-2';

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
  );
var data;
var options;

export function GraficoDash(props) { 
  var vetorDados=props.dados
    var vetorValor=[]
    var vetorDias=[]
    for(var i=0; i<vetorDados.length; i++){
      vetorValor.push(vetorDados[i][1])
      vetorDias.push(vetorDados[i][0].substring(8,10))
    }
  configGrafico(vetorValor, vetorDias, props.cor, props.titulo);
  return (
  <>
  <Line data={data} options={options}/>
  </>
  );
}

 
function configGrafico(v1, v2, cor, titulo){
  const labels=v2
  options= {
    scale: {
        grid:{
            borderColor:"red"
        }
        
    },
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
  }
  
  data = {
    labels,
    datasets: [
      {
        data: v1,
        borderColor: cor,
        pointRadius: 0,
      },
    ],
  };
}