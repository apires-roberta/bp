import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import apiLogin from "../apiLogin"
import CardDados from "../components/CardDados";
import { GraficoDash } from "../components/GraficoDash";
import apiCampanha from "../apiCampanha";
import dados from "../ColetarDadors";
import Rodape from "../components/Rodape";
import ip from '../ip';
function DashMensal(){
    console.log(dados);
    const[vetorDados, setVetorDados] = useState([[0,[],0,[],[],0]]);
    useEffect(() => {
        const interval = setInterval(()=>{
        setVetorDados(dados)
        return clearInterval(interval)
    },200)
        
    },[])
    console.log(vetorDados)
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
      margin-top: 1%;
    `;
    const estiloDiv = {
        marginLeft: "10%",
        marginTop: "6%"
    }
    const estiloDiv2 = {
        width: "100%",
        height: "55vh",
        marginTop: "5%"
    }
    const vetorMeses={7:"JUL", 8:'AGO', 9:'SET', 10:'OUT', 11:'NOV', 12:'DEZ'}
    return (
        <> 
            <Menu funcaoDark={toggleTheme} funcao="" />
            <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
                    <DivInfo> 
                        {
                            vetorDados.map((vetor)=>(
                                <div style={estiloDiv2}>
                                    <CardDados style={estiloDiv} id={vetor[0]} mes={vetorMeses[vetor[2]]}/>
                                    <div id="grafico" className="graficoDash" onClick={() => dadosDashboardDetalhado(vetor[5])}>
                                        <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dados={vetor[1]}/>
                                    </div>
                                </div>
                            )
                            ) 
                        }
                    </DivInfo>
                    <Rodape/>
                </Fragment>
            </ThemeProvider>

        </>
    );
}

export default DashMensal;

function redirecionar(pagina) {
    window.location.href = `http://${ip}:3000/` + pagina;
}

function dadosDashboardDetalhado(mesSelecionado){
    sessionStorage.setItem("mes", mesSelecionado)
    redirecionar("DashboardDetalhado")
}