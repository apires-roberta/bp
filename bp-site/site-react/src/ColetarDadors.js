import apiCampanha from "./apiCampanha";
import apiLogin from "./apiLogin";
import React, { Fragment, useState, useEffect } from "react";
var idCampanha;
var dados = [];
var diaInicio;
var dia;
var data = new Date();
apiLogin.get(`/bp/ong/${sessionStorage.getItem("idOng")}`).then((resposta) => {
    diaInicio = resposta.data.dataCriacaoConta
    apiCampanha.get(`/dash/${sessionStorage.getItem("idOng")}/${diaInicio}`).then((resposta) => {
        for (var t = 0; t < resposta.data.length; t++) {
            var vetor = resposta.data[t];
            var dias = [];
            idCampanha = vetor.idCampanha;
            var vetorDias = vetor.doacoesDia;
            var mes = vetor.mes
            if(mes == data.getMonth()+1){
                dia = data.getDate();
            }
            else if(mes==9 || mes==11){
                dia = 30;
            }
            for (var i = 1; i <= dia; i++) {
                var qtdDoacao = 0;
                for (var j = 0; j < vetorDias.length; j++) {
                    if (vetorDias[j][1] == `2022-${(""+vetor.mes).length<2?"0"+vetor.mes:vetor.mes}-${("" + i).length < 2 ? "0" + i : i}`) {
                        qtdDoacao = vetorDias[j][0]
                        break;
                    }
                } 
                dias.push([`2022-${(""+vetor.mes).length<2?"0"+vetor.mes:vetor.mes}-${("" + i).length < 2 ? "0" + i : i}`, qtdDoacao])
            }
            dados.push([idCampanha, dias, vetor.mes, resposta.data[t].doacoesSemana, resposta.data[t].doacoesValor,t])
        }
    })
});


export default dados;