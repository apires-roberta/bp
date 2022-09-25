import React from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Pie } from 'react-chartjs-2';
import styled from "styled-components";

ChartJS.register(ArcElement, Tooltip, Legend);
var data;
var options;

export function Grafico(props) {
  const H3Grafico = styled.h3`
    padding-top: 8%;
    color: ${({ theme }) => theme.logo};
  `;
  var valores = calcularRestante(props.valorDesejado,props.valorAtual)
  configGrafico(valores);
  return (
  <>
  <Pie data={data} options={options}/>
  <H3Grafico>{valores[0].toFixed(2).replace(".",",")}% (R${props.valorAtual.toFixed(2).replace(".",",")})</H3Grafico>
  </>
  );
}

function calcularRestante(valorDesejado, valorAtual){
  if(valorAtual<=valorDesejado){
      return [(100*valorAtual)/valorDesejado, 100-(100*valorAtual)/valorDesejado];
  }
  else{
      return [(100*valorAtual)/valorDesejado]
  }
  
}

function configGrafico(valores){
  options= {
    maintainAspectRatio: false,
    segmentShowStroke: false,
    plugins: {
        tooltip:{
            enabled: false,
        },
    },
    events: []
  }
  
  data = {
    datasets: [
      {
        data: valores,
        backgroundColor: [
          '#01396F',
          'white',
        ],
      borderColor: [
        '#01396F',
        'rgb(150, 150, 150)',
      ],
        borderWidth: 4,
      },
    ],
  };
}

/*
    100 - valorDesejado
     x  - valorAtual

     x = (100*valorAtual)/valorDesejado
*/