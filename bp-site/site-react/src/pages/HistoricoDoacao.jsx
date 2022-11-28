import Input from "../components/Input";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import apiLogin from "../apiLogin"
import Rodape from "../components/Rodape";
import CardHistorico from "../components/CardHistorico";
import ip from '../ip';

function HistoricoDoacao() {
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
    const DivLogin = styled.div`
        width: 60%;
        margin-left: 20%;
        margin-top: 10%;
        padding-top: 1.5%;
        color: #01396F;
        height: 80vh;
        box-shadow: ${({ theme }) => theme.borda};
        color: ${({ theme }) => theme.azulClaro};
    `;
    const Titulo = styled.h1`
        text-align: center;
        margin-top: 10%;
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
        width: 50%;
        height: 7vh;
        margin-top: 5%;
        margin-bottom: 5%;
        margin-left: 25%;
    `;
    const aCentro={
        marginLeft: "33%"
    };

    const BotaoPequeno = styled.button`
        text-align: center;
        background-color: ${({ theme }) => theme.azulClaro};
        color: white;
        border: none;
        border-radius: 50px;
        box-shadow: ${({ theme }) => theme.borda};
        cursor:pointer;
        font-size: 16px;
        width: 10%;
        height: 5vh;
        margin-top: 1%;
        margin-bottom: 5%;
        font-size: 20px;
    `;

    const Texto = styled.span`
        color: ${({ theme }) => theme.logo}
    `;

    const estilo = {
        textAlign: "center",
        fontSize: "20px",
        marginTop: "2%"
    }

    const estiloButao={
        marginLeft: "5%"
    }
    return (
        <>
            <Menu funcaoDark={toggleTheme} funcao="cadastro" />
            <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
            <DivLogin>
                <Titulo>Histórico de doações</Titulo>
                <CardHistorico></CardHistorico>
            </DivLogin>
            <Rodape/>
            </Fragment>
            </ThemeProvider>
        </>
    );
  }
  
  export default HistoricoDoacao;

  function redirecionar(pagina) {
    window.location.href = `http://${ip}:3000/`+pagina;
  }