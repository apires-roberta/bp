import apiCampanha from "../apiCampanha";
import React, { Fragment, useState, useEffect } from "react";
import { GraficoBar } from "./GraficoBar";
import { GraficoDash } from "./GraficoDash";

function ColetarDados() {
    var estado = sessionStorage.getItem("estado");
    const [theme, setTheme] = useState("light");
    const [vetorInformacoes, setVetor] = useState([]);
    var dadosDoador = [];
    var dadosOng = [];
    var dadosDoacao = [];
    var dadosCampanha = [];
    var dia = new Date();
    var diaAtual = dia.getDate();
    var vetorAtualDoador = [];
    var vetorAtualOng = [];
    var vetorAtualDoacao = [];
    var vetorAtualCampanha = [];
    useEffect(() => {
        setVetor([])
        apiCampanha.get(`/dash/${estado}`).then((resposta) => {
            var vetor = resposta.data;
            vetorAtualDoador = [];
            vetorAtualOng = [];
            vetorAtualDoacao = [];
            vetorAtualCampanha = [];
            for (var i = 0; i < vetor.length; i++) {
                if (vetor[i][0] == "Doador") {
                    dadosDoador.push([vetor[i][1], vetor[i][2]]);
                }
                else if (vetor[i][0] == "Ong") {
                    dadosOng.push([vetor[i][1], vetor[i][2]]);
                }
                else if (vetor[i][0] == "Doacao") {
                    dadosDoacao.push([vetor[i][1], vetor[i][2]]);
                }
                else if (vetor[i][0] == "Campanha") {
                    dadosCampanha.push([vetor[i][1], vetor[i][2]]);
                }
            }
            var vetorDias = []
                if(diaAtual<7){
                    for(var d=6; d>=0; d--){
                        if(diaAtual-d<=0){
                            vetorDias.push(dia.getFullYear() + "-" + (dia.getMonth()) + "-" + (34-d))
                        }
                        else{
                            vetorDias.push(dia.getFullYear() + "-" + (dia.getMonth() + 1) + "-0" + (diaAtual-d))
                        }
                    }
                }
                else{
                    for(var d=0; d<7; d++){
                        vetorDias.push(dia.getFullYear() + "-" + (dia.getMonth() + 1) + "-0" + (d < 10 ? "0" + d : d))
                    }
                }
            console.log(dadosOng)
            if (dadosOng.length > 0) {
                var vetorDiaAtual = []
                
                for (var i = 0; i <= 6; i++) {
                    vetorDiaAtual = []
                    for (var j = 0; j < dadosOng.length; j++) {
                        if (dadosOng[j][1] == vetorDias[i]) {
                            vetorDiaAtual = [dadosOng[j][1], dadosOng[j][0]];
                            break;
                        }
                    }
                    if (vetorDiaAtual.length == 0) {
                        vetorDiaAtual = [vetorDias[i], 0]
                    }
                    vetorAtualOng.push(vetorDiaAtual)
                }
            }
            else {
                for (var i = 0; i <= 6; i++) {
                    vetorAtualOng.push([vetorDias[i], 0])
                }
            }

            if (dadosDoador.length > 0) {
                var vetorDiaAtual = []
                for (var i = 0; i <= 6; i++) {
                    vetorDiaAtual = []
                    for (var j = 0; j < dadosDoador.length; j++) {
                        if (dadosDoador[j][1] == vetorDias[i]) {
                            vetorDiaAtual = [dadosDoador[j][1], dadosDoador[j][0]];
                            break;
                        }
                    }
                    if (vetorDiaAtual.length == 0) {
                        vetorDiaAtual = [vetorDias[i], 0]
                    }
                    vetorAtualDoador.push(vetorDiaAtual)
                }
            }
            else {
                for (var i = 0; i <= 6; i++) {
                    vetorAtualDoador.push([vetorDias[i], 0])
                }
            }

            if (dadosDoacao.length > 0) {
                var vetorDiaAtual = []
                for (var i = 0; i <= 6; i++) {
                    vetorDiaAtual = []
                    for (var j = 0; j < dadosDoacao.length; j++) {
                        if (dadosDoacao[j][1] == vetorDias[i]) {
                            vetorDiaAtual = [dadosDoacao[j][1], dadosDoacao[j][0]];
                            break;
                        }
                    }
                    if (vetorDiaAtual.length == 0) {
                        vetorDiaAtual = [vetorDias[i], 0]
                    }
                    vetorAtualDoacao.push(vetorDiaAtual)
                }
            }
            else {
                for (var i = 0; i <= 6; i++) {
                    vetorAtualDoacao.push([vetorDias[i], 0])
                }
            }

            if (dadosCampanha.length > 0) {
                var vetorDiaAtual = []
                for (var i = 0; i <= 6; i++) {
                    vetorDiaAtual = []
                    for (var j = 0; j < dadosCampanha.length; j++) {
                        if (dadosCampanha[j][1] == vetorDias[i]) {
                            vetorDiaAtual = [dadosCampanha[j][1], dadosCampanha[j][0]];
                            break;
                        }
                    }
                    if (vetorDiaAtual.length == 0) {
                        vetorDiaAtual = [vetorDias[i], 0]
                    }
                    vetorAtualCampanha.push(vetorDiaAtual)
                }
            }
            else {
                for (var i = 0; i <= 6; i++) {
                    vetorAtualCampanha.push([vetorDias[i], 0])
                }
            }
            var vetorTeste=[];
            vetorTeste.push(vetorAtualDoador)
            vetorTeste.push(vetorAtualOng)
            vetorTeste.push(vetorAtualDoacao)
            vetorTeste.push(vetorAtualCampanha)
            setVetor([vetorTeste]);
            console.log(vetorAtualOng)
        });
    }, []) 
    return (
        <>
            {
                vetorInformacoes.map((vetor) => (
                    <>
                        <div className="graficoEstado">
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={vetor[0]} titulo="Quantidade de novos doadores (nos ultimos 7 dias)" />
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={vetor[1]} titulo="Quantidade de novas ONG (nos ultimos 7 dias)" />
                        </div>
                        <div className="graficoEstado">
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={vetor[2]} titulo="Quantidade de novas doações (nos ultimos 7 dias)" />
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={vetor[3]} titulo="Quantidade de novas campanhas (nos ultimos 7 dias)" />
                        </div>
                    </>
                ))
            }
        </>
    );
}

export default ColetarDados;