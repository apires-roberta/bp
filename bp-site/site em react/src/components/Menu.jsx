import logo from "../img/logo-2.png"
import lua from "../img/Moon Symbol.png"
import sol from "../img/Vector.png"
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import contaBranco from "../img/conta em branco.png"
import contaPreto from "../img/conta em preto.png"
import notificacaoBranco from "../img/notificacao em branco.png"
import notificacaoPreto from "../img/notificacao em preto.png"


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
    `;

    const Title = styled.span`
        color:${({ theme }) => theme.logo};
        font-size: 32px;
        float: left;
        margin-top: 1.5%;
    `;

    const MenuLateral = styled.span`
        font-size:40px;
        cursor:pointer;
        margin-left: 4.25%;
        float: left;
        color: ${({ theme }) => theme.azulClaro};
    `;

    const Botao = styled.button`
        text-align: center;
        background-color: ${({ theme }) => theme.azulClaro};
        color: white;
        border: none;
        border-radius: 50px;
        box-shadow: ${({ theme }) => theme.borda};
        cursor:pointer;
    `;

    if (props.funcao == "semCadastro") {
        return (
            <>
                <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                    <Fragment>
                        <GlobalTheme />
                        <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap" rel="stylesheet"></link>
                        <EstiloMenu>
                            <img className="logo" src={logo} />
                            <img onClick={toggleTheme} className="tema" src={theme === "light" ? lua : sol} />
                            <Title>bp</Title>
                        </EstiloMenu>
                        <div id="mySidenav" className="sidenav">
                            <a href="javascript:void(0)" className="closebtn" onClick={closeNav}>&times;</a>
                            <a href="perfilDoador.html">Perfil</a>
                            <a href="Campanhas.html">Campanhas</a>
                        </div>
                        <MenuLateral onClick={openNav}>&#9776;</MenuLateral>
                    </Fragment>
                </ThemeProvider>
            </>
        );
    }
    else {
        return (
            <>
                <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                    <Fragment>
                        <GlobalTheme />
                        <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap" rel="stylesheet"></link>
                        <EstiloMenu>
                            <img className="logo" src={logo} />
                            <Title>bp</Title>
                            <div className="divDireita">
                                <img onClick={toggleTheme} className="tema" src={theme === "light" ? lua : sol} />
                                <Botao onClick={redirecionar}>Campanhas</Botao>
                                <img src={theme === "light" ? notificacaoPreto : notificacaoBranco} alt="" />
                                <img src={theme === "light" ? contaPreto : contaBranco} alt="" />
                            </div>
                        </EstiloMenu>
                        <div id="mySidenav" className="sidenav">
                            <a href="javascript:void(0)" className="closebtn" onClick={closeNav}>&times;</a>
                            <a href="perfilDoador.html">Perfil</a>
                            <a href="Campanhas.html">Campanhas</a>
                        </div>
                        <MenuLateral onClick={openNav}>&#9776;</MenuLateral>
                    </Fragment>
                </ThemeProvider>
            </>
        );
    }

}

export default Menu;

function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
function redirecionar() {
    window.location.href = "http://localhost:3000/login";
}

