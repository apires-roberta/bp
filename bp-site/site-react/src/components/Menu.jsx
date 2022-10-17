import logo from "../img/logo-2.png"
import lua from "../img/Moon Symbol.png"
import sol from "../img/Vector.png"
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import contaBranco from "../img/conta em branco.png";
import contaPreto from "../img/conta em preto.png";
import notificacaoBranco from "../img/notificacao em branco.png";
import notificacaoPreto from "../img/notificacao em preto.png";
import BarraPesquisa from "./BarraPesquisa";
import MenuPerfil from "./MenuPerfil";
import imgPesquisaBranco from "../img/pesquisaImagemBranco.svg";
import imgPesquisaPreto from "../img/pesquisaImagemPreto.svg";
import campanhaPreto from "../img/campanhaPreto.svg";
import campanhaBranco from "../img/campanhaBranco.svg";


function Menu(props) {
    const [theme, setTheme] = useState("light");

    const toggleTheme = () => {
        if (theme === "light") {
            window.localStorage.setItem("theme", "dark");
            setTheme("dark");
            props.funcaoDark("dark")
        } else {
            window.localStorage.setItem("theme", "light");
            setTheme("light");
            props.funcaoDark("light")
        }
    };

    useEffect(() => {
        const localTheme = window.localStorage.getItem("theme");
        localTheme && setTheme(localTheme);
    }, []);

    const EstiloMenu = styled.div`
        width: 100%;
        height: 10vh;
        box-shadow: ${({ theme }) => theme.borda};
        background-color:${({ theme }) => theme.menu};
        position: fixed;
        z-index:99;
        top: 0;
    `;

    const Title = styled.span`
        color:${({ theme }) => theme.logo};
        font-size: 32px;
        float: left;
        margin-top: 1.5%;
    `;

    const MenuLateralBotao = styled.span`
        font-size:40px;
        cursor:pointer;
        margin-left: 4.25%;
        float: left;
        color: ${({ theme }) => theme.azulClaro};
        position: fixed;
        top: 10%;
    `;

    const estilo = {
        float: "right",
        marginRight: "10%"
    }

    const MenuLateral = styled.div`
        height: 100%;
        width: 0;
        position: fixed;
        z-index: 100;
        top: 0;
        left: 0;
        background-color: ${({ theme }) => theme.body};
        color: white;
        overflow-x: hidden;
        transition: 0.5s;
        padding-top: 60px;
    `;

    const A = styled.span`
        color: ${({ theme }) => theme.logo};
    `;

    const pesquisa = {
        display: "none",
    }

    if (props.funcao === "cadastro") {
        return (
            <>
                <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                    <Fragment>
                        <GlobalTheme />
                        <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap" rel="stylesheet"></link>
                        <EstiloMenu>
                            <img alt="" onClick={()=>redirecionar("home")} className="logo" src={logo} />
                            <img alt="" onClick={toggleTheme} className="tema" src={theme === "light" ? lua : sol} />
                            <Title>bp</Title>
                        </EstiloMenu>
                    </Fragment>
                </ThemeProvider>
            </>
        );
    }
    else{
        return (
            <>
                <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                    <Fragment>
                        <GlobalTheme />
                        <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap" rel="stylesheet"></link>
                        <EstiloMenu>
                            <img alt="" onClick={()=>redirecionar("home")} className="logo" src={logo} />
                            <Title>bp</Title>
                            <div id={"idPesquisa"} style={pesquisa}>
                            <BarraPesquisa/>
                            </div>
                            <div className="divDireita">
                                <img onMouseEnter={fecharMenu} src={theme === "light" ? notificacaoPreto : notificacaoBranco} title="Notificação" alt="" />
                                <div onMouseEnter={manterMenu}><img src={theme === "light" ? contaPreto : contaBranco} alt="" /></div>
                                <img onMouseEnter={fecharMenu} alt="" style={estilo} onClick={toggleTheme} className="tema" src={theme === "light" ? lua : sol} title={theme === "light" ? "Modo escuro" : "Modo claro"} />
                                <img onClick={()=>redirecionar("campanha")}src={theme === "light" ? campanhaPreto : campanhaBranco} title="Campanhas" alt="" />
                                <img onClick={trocarBarra} src={theme === "light" ? imgPesquisaPreto : imgPesquisaBranco} title="Pesquisar" alt="" />
                            </div>
                            <MenuPerfil/>
                        </EstiloMenu>
                    </Fragment>
                </ThemeProvider>
            </>
        );
    }
}

export default Menu;

function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/"+pagina;
}

function manterMenu(){
    document.getElementById("divMenu").style.display="block";
}

function fecharMenu(){
    document.getElementById("divMenu").style.display="none";
}

function trocarBarra(){
    if(document.getElementById("idPesquisa").style.display=="none"){
        abrirPesquisa()
    }
    else{
        fecharPesquisa()
    }
}

function abrirPesquisa(){
    document.getElementById("idPesquisa").style.display="block";
}

function fecharPesquisa(){
    document.getElementById("idPesquisa").style.display="none";
}