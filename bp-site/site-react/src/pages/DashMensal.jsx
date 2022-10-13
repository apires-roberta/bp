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

function DashMensal() {
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
    const estiloDiv = {
        marginLeft: "10%",
        marginTop: "6%"
      }
    return (
        <>
            <Menu funcaoDark={toggleTheme} funcao="" />
            <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
                    <DivInfo>
                        <CardDados style={estiloDiv} nome="Campanha" valorCampanha="1000,00" descricao="descrição" mes="JAN"/>
                        <div className="graficoDash" onClick={()=>redirecionar("DashboardDetalhado")}>
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dia={31}/>
                        </div>
                    </DivInfo>
                    <DivInfo>
                        <CardDados style={estiloDiv} nome="Campanha" valorCampanha="1000,00" descricao="descrição" mes="FEV"/>
                        <div className="graficoDash">
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dia={28}/>
                        </div>
                    </DivInfo>
                    <DivInfo>
                        <CardDados style={estiloDiv} nome="Campanha" valorCampanha="1000,00" descricao="descrição" mes="MAR"/>
                        <div className="graficoDash">
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dia={31}/>
                        </div>
                    </DivInfo>
                    <DivInfo>
                        <CardDados style={estiloDiv} nome="Campanha" valorCampanha="1000,00" descricao="descrição" mes="ABR"/>
                        <div className="graficoDash">
                            <GraficoDash cor={theme === "light" ? "#01396F" : "#0070DC"} dia={30}/>
                        </div>
                    </DivInfo>
                </Fragment>
            </ThemeProvider>

        </>
    );
}

export default DashMensal;

function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/" + pagina;
}

