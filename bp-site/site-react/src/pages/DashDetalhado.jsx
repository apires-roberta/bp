import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import apiLogin from "../apiLogin"
import CardDados from "../components/CardDados";
import { GraficoDash } from "../components/GraficoDash";
import { GraficoBar } from "../components/GraficoBar";
import dados from "../ColetarDadors";
import Rodape from "../components/Rodape";
 
function DashDetalhado() {
    
    const [dadosMesEscolhido, setMesAtual] = useState([]);
    const [dadosMesAnterior, setMesAnterior] = useState([]);
    var mesEscolhido = sessionStorage.getItem("mes")
    useEffect(() => {
        setMesAtual([dados[mesEscolhido]]);
        setMesAnterior([mesEscolhido < 1 ? null : dados[mesEscolhido - 1]]);
    }, []);

    const [theme, setTheme] = useState("light");

    const toggleTheme = () => {
        if (theme === "light") {
            window.localStorage.setItem("theme", "dark");
            setTheme("dark");
        } else {
            window.localStorage.setItem("theme", "light");
            setTheme("light");
        }
    };
    useEffect(() => {
        const localTheme = window.localStorage.getItem("theme");
        localTheme && setTheme(localTheme);
    }, []);
    const DivInfo = styled.div`
      width: 100%;
      margin-top: 5%;
      height: 50vh;
    `;
    const P = styled.p`
        font-size: 24px;
        padding: 2%;
        color: ${({ theme }) => theme.logo};
    `;
    const Div = styled.div`
        box-shadow: ${({ theme }) => theme.borda};
        width: 80%;
        float: left;
        margin-top: 5%;
        margin-left: 10%;
    `;
    const Span = styled.span`
        font-size: 24px;
        color: #E67D86;
    `;
    const estiloDiv = {
        marginLeft: "10%",
        marginTop: "6%"
    }
    const estiloNovo = {
        marginTop: "2%"
    }
    const estilo = {
        height: "130vh"
    } 
    const vetorMeses = { 7: "JUL", 8: 'AGO', 9: 'SET', 10: 'OUT', 11: 'NOV', 12: 'DEZ' }
    if (dadosMesAnterior[0] != null) {
        var qtdDoacaoMesAtual = somarQuantidade(dadosMesEscolhido[0][1])
        var qtdDoacaoMesAntigo = somarQuantidade(dadosMesAnterior[0][1])
        var qtdValorMesAtual = somarValor(dadosMesEscolhido[0][4][0])
        var qtdValorMesAntigo = somarValor(dadosMesAnterior[0][4][0])
        return (
            <>
                <Menu funcaoDark={toggleTheme} funcao="" />
                <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                    <Fragment>
                        <GlobalTheme />
                        <div style={estilo}>                            
                                {
                                    dadosMesEscolhido.map((dados) => (
                                        <>
                                        <DivInfo>
                                            <CardDados style={estiloDiv} id={dados[0]} mes={vetorMeses[dados[2]]} />
                                            <div onClick={()=>redirecionar("Dashboard")} className="graficoDash">
                                                <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={dados[1]} />
                                            </div>
                                            </DivInfo>
                                            <DivInfo>
                                            <div className="graficoBarraSemanal">
                                                <GraficoBar cor={theme === "light" ? "#01396F" : "#0070DC"} grafico="Semanal" dados={dados[3][0]} />
                                            </div>
                                            <div className="graficoBarra">
                                                <GraficoBar cor={theme === "light" ? "#E67D86" : "#E67D86"} dados={dados[4][0]} />
                                            </div>
                                        </DivInfo>
                                        <Div style={estiloNovo}>
                                            <P>Baseado no valor da campanha X a quantidade de doações, nesse mês tiveram <Span>{qtdDoacaoMesAtual > qtdDoacaoMesAntigo ? "maior" : "menor"}</Span> doações que a habitual, porém com um valor <Span>{qtdValorMesAtual > qtdValorMesAntigo ? "maior" : "menor"}</Span>.</P>
                                        </Div>
                                        </>
                                    ))

                                }
                        </div>
                        <Rodape />
                    </Fragment>
                </ThemeProvider>

            </>
        );
    }
    else {
        return (
            <>
                <Menu funcaoDark={toggleTheme} funcao="" />
                <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                    <Fragment>
                        <GlobalTheme />
                        <div style={estilo}>
                        {
                                    dadosMesEscolhido.map((dados) => (
                                        <>
                                        <DivInfo>
                                            <CardDados style={estiloDiv} id={dados[0]} mes={vetorMeses[dados[2]]} />
                                            <div className="graficoDash">
                                                <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={dados[1]} />
                                            </div>
                                            </DivInfo>
                                            <DivInfo>
                                            <div className="graficoBarraSemanal">
                                                <GraficoBar cor={theme === "light" ? "#01396F" : "#0070DC"} grafico="Semanal" dados={dados[3][0]} />
                                            </div>
                                            <div className="graficoBarra">
                                                <GraficoBar cor={theme === "light" ? "#E67D86" : "#E67D86"} dados={dados[4][0]} />
                                            </div>
                                        </DivInfo>
                                        </>
                                    ))

                                }
                        </div>
                        <Rodape />
                    </Fragment>
                </ThemeProvider>

            </>
        );
    }
}

export default DashDetalhado;

function somarQuantidade(vetor) {
    var soma = 0;
    for (var i = 0; i < vetor.length; i++) {
        soma += vetor[i][1]
    }
    return soma;
}

function somarValor(vetor) {
    var soma = 0;
    for (var i = 0; i < vetor.length; i++) {
        soma += (vetor[i] * ((i + 1) * 10))
    }
    return soma;
}

function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/" + pagina;
  }