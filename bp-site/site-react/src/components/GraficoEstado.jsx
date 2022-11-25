import apiCampanha from "../apiCampanha";
import React, { Fragment, useState, useEffect } from "react";
import { GraficoBar } from "./GraficoBar";
import { GraficoDash } from "./GraficoDash";
import apiAnalytics from "../apiAnalytics"
import { height } from "@mui/system";
import styled from "styled-components";

function ColetarDados() {
    function alterarDados(estado){
        var tipo = document.getElementById("tipoCampanha").value
        apiCampanha.get(`/dash/alterar/${tipo}/${estado}`).then((resposta) => {
              if (resposta.status === 200) {
                alert(resposta.data)
              }
            })
    }
    var estado = sessionStorage.getItem("estado");
    const [theme, setTheme] = useState("light");
    const [vetorInformacoes, setVetor] = useState([]);
    const [vetorAlimento, setAlimento] = useState([]);
    const [vetorClima, setClima] = useState([]);
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
    var vetorEstadoCapital = {
        'DF':'Brasilia','MT':'Cuiaba','MS':'Campo grande','GO':'Goiania','SP':'Sao paulo','RJ':'Rio de janeiro',
        'ES':'Vitoria','MG':'Belo horizonte','SC':'Florianopolis','RS':'Porto alegre','PR':'Curitiba','PB':'Joao pessoa',
        'BA':'Salvador','PI':'Teresina','PE':'Recife','PA':'Belem','RN':'Natal','RO':'Porto velho',
        'RR':'Boa vista','SE':'Aracaju','TO':'Palmas','AC':'Rio branco','AL':'Maceio','AP':'Macapa',
        'AM':'Manaus','CE':'Fortaleza','MA':'Sao luis'}
    useEffect(() => {
        setVetor([])
        apiCampanha.get(`/dash/${estado}`).then((resposta) => {
            var vetor = resposta.data;
            console.log("dados", vetor)
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
                    var contadorDias=25+diaAtual
                    for(var d=6; d>=0; d--){
                        if(diaAtual-d<=0){
                            vetorDias.push(dia.getFullYear() + "-" + (dia.getMonth()) + "-" + (contadorDias))
                            contadorDias++;
                        }
                        else{
                            vetorDias.push(dia.getFullYear() + "-" + (dia.getMonth() + 1) + "-0" + (diaAtual-d))
                        }
                    }
                }
                else{
                    for(var d=6; d>=0; d--){
                        vetorDias.push(dia.getFullYear() + "-" + (dia.getMonth() + 1) + "-" + ((diaAtual-d) < 10 ? "0" + (diaAtual-d) : (diaAtual-d)))
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
        const interval = setInterval(()=>{
            apiAnalytics.get(`/Alimento/${estado}`).then((resposta) => {
                  console.log(resposta.data)
                  setAlimento(resposta.data)
            })
            apiAnalytics.get(`/Clima/${vetorEstadoCapital[estado]}`).then((resposta) => {
                console.log(resposta.data)
                setClima(resposta.data)
          })
        return clearInterval(interval)
        },200)
    }, []) 
    const estilo = {
        width: '100%',
        marginTop: '10%'
    }
    const Select = styled.select`
        margin-top: 5%;
        text-align: center;
        width: 40%;
        border-radius: 30px;
        height: 6vh;
        background-color: transparent;
        border: ${({ theme }) => theme.bordaInput} 2px solid;
        color: ${({ theme }) => theme.letraInput};
        float: none;
    `;

    const Option = styled.option`
        background-color: ${({ theme }) => theme.menu};
    `;

    const Botao = styled.button`
        text-align: center;
        background-color: ${({ theme }) => theme.azulClaro};
        color: white;
        border: none;
        border-radius: 50px;
        box-shadow: ${({ theme }) => theme.borda};
        cursor:pointer;
        font-size: 20px;
        width: 40%;
        height: 5vh;
        margin-top: 6%;
        float: none;
    `;
    return (
        <>
            {
                vetorInformacoes.map((vetor) => (
                    <>
                        <div className="graficoEstado">
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={vetor[0]} titulo="Quantidade de novos doadores (nos ultimos 7 dias)" />
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={vetor[1]} titulo="Quantidade de novas ONG (nos ultimos 7 dias)" />
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={vetorAlimento} titulo="Preço da cesta basica X mes" />
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={[]} titulo="Problemas respiratorios X dia" />
                        </div>
                        <div className="graficoEstado">
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={vetor[2]} titulo="Quantidade de novas doações (nos ultimos 7 dias)" />
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={vetor[3]} titulo="Quantidade de novas campanhas (nos ultimos 7 dias)" />
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={vetorClima} titulo="Temperatura X dia" />
                            <div style={estilo}>
                                <span>Qual vai ser recomendado?</span><br></br>
                                <Select id="tipoCampanha">
                                    <Option value=""></Option>
                                    <Option value="1">Fome</Option>
                                    <Option value="2">Saude</Option>
                                    <Option value="3">Roupa</Option>
                                    <Option value="4">Todos</Option>
                                    <Option value="0">Retirar</Option>
                                </Select><br></br>
                                <Botao onClick={()=>alterarDados(estado)}>Recomendar</Botao>
                            </div>
                        </div>
                            
                    </>
                ))
            }
        </>
    );
}

export default ColetarDados;