import { useEffect, useState } from "react";
import React from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { Chart } from 'react-chartjs-2';
import { Pie } from 'react-chartjs-2';
import api from "../api";

ChartJS.register(ArcElement, Tooltip, Legend);
var data;
var options;

export function Grafico(props) {
  var valores = calcularRestante(props.valorDesejado,props.valorAtual)
  configGrafico(valores);
  return (
  <>
  <Pie data={data} options={options}/>
  <h3>{valores[0]}%</h3>
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