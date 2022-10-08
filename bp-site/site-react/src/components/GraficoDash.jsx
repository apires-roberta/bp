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
    const vetorValor=[]
    const vetorDias=[]
    for (var i=0;i<props.dia;i++){
        vetorValor.push(getRandomInt(0,100));  
        vetorDias.push(i+1)                          
    }
  configGrafico(vetorValor, vetorDias, props.cor);
  return (
  <>
  <Line data={data} options={options}/>
  </>
  );
}


function configGrafico(v1, v2, cor){
  const labels=v2
  options= {
    scale: {            
        max: 100,
        grid:{
            borderColor:"red"
        }
        
    },
    plugins: {
        legend: {
            display: false,
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

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min) + min);
  }