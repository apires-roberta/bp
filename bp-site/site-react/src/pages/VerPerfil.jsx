import styled from "styled-components";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import React, { Fragment, useState, useEffect } from "react";
import Rodape from "../components/Rodape";
function VerPerfil(){
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
    const Cartao = styled.div`
        margin-top:8%;
        width:70%;
        border-radius:14px;
        box-shadow: ${({ theme }) => theme.bordaFeed};
        background-color: ${({ theme }) => theme.body};
        color: ${({ theme }) => theme.logo};
        margin-left:15%;
        position:relative;
    `;
    const DivLogin = styled.div`
    width: 15%;
    float: left;
    margin-left: 25%;
    border-radius:10px;
    margin-top: 8.5%;
    background-color:${({ theme }) => theme.body};
    position:relative;
    z-index:98;
    box-shadow: ${({ theme }) => theme.bordaFeed};
    color: ${({ theme }) => theme.azulClaro};
    `;
    const Divtext = styled.div`
    height: 10vh;
    margin-top:10%;
    float: left;
    font-size:20px;
    text-align: center;
    width: 100%;
    `;
    const DivImagem = styled.div`
    position: absolute;
    width: 60%;
    margin-left: 20%;
    margin-top: 8%;
    `;
    const fotoFundo={
        height: "40vh",
        width: "100%",
        borderRadius: "10px 10px 0px 0px"
    }
    const fotoPerfil= {
        borderRadius: "3%",
        width: "100%"
    }
    const nomeOng={
        fontSize: "40px",
        color: "white",
    }
    const slogan={
        fontSize: "20px",
        color: "white",
    }
    const alinhaBtn={
        marginLeft: "51.5%",
        width: "50%",
        paddingTop:"26%",
    }
    const estiloBtn={
        marginLeft:"12%",
        marginTop: "5%"
    }
    return(
        <>
        <Menu funcaoDark={toggleTheme} funcao="cadastro" />
            <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
                <DivImagem>
                    <img style={fotoFundo} src="http://guiadefontes.msf.org.br/wp-content/uploads/2017/01/org-actionaid-1024x443.jpg   " alt=""/>
                </DivImagem>
                    <DivLogin>
                        <img alt="" style={fotoPerfil} src="https://captadores.org.br/wp-content/uploads/2021/04/actionaid-logo-vector.png" width="170" height="170"/>
                    </DivLogin>
                </Fragment>
            </ThemeProvider>

        </>
    );
}

export default VerPerfil;