import Input from "../components/Input";
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

function DashDetalhado(){
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
        font-size: 32px;
        padding: 2%;
        color: ${({ theme }) => theme.logo};
    `;
    const Div = styled.div`
        box-shadow: ${({ theme }) => theme.borda};
        width: 40%;
        float: left;
        margin-top: 8%;
        margin-left: 10%;
    `;
    const Span = styled.span`
        font-size: 36px;
        color: #E67D86;
    `;
    const estiloDiv = {
        marginLeft: "10%",
        marginTop: "6%"
      }
    const estiloNovo = {
        marginTop:"2%"
    }
      return (
        <>
            <Menu funcaoDark={toggleTheme} funcao="" />
            <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
                    <DivInfo>
                        <CardDados style={estiloDiv} nome="Campanha" valorCampanha="1000,00" descricao="descrição" mes="JAN"/>
                        <div className="graficoDash">
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dia={31}/>
                        </div>
                    </DivInfo>
                    <DivInfo>
                        <div className="graficoBarraSemanal">
                            <GraficoBar cor={theme === "light" ? "#01396F" : "#0070DC"} grafico="Semanal"/>
                        </div>
                    </DivInfo>
                    <Div style={estiloNovo}>
                        <P>Baseado no valor da campanha X a quantidade de doações, nesse mês tiveram <Span>menos</Span> doações que a habitual, porém com um valor <Span>maior</Span>.</P>
                    </Div>
                    <div className="graficoBarra">
                        <GraficoBar cor={theme === "light" ? "#E67D86" : "#E67D86"} dia={31}/>
                    </div>
                </Fragment>
            </ThemeProvider>

        </>
    );
}

export default DashDetalhado;