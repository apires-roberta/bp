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
    const EstiloMenu2 = styled.div`
        width: 100%;
        height: 10vh;
        box-shadow: ${({ theme }) => theme.borda};
        background-color:${({ theme }) => theme.menu};
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

    const Botao = styled.button`
        text-align: center;
        background-color: ${({ theme }) => theme.azulClaro};
        color: white;
        border: none;
        border-radius: 50px;
        box-shadow: ${({ theme }) => theme.borda};
        cursor:pointer;
    `;
    const estilo = {
        float: "right",
        marginRight: "10%"
    }

    const MenuLateral = styled.div`
        height: 100%;
        width: 0;
        position: fixed;
        z-index: 1;
        top: 0;
        left: 0;
        background-color: ${({ theme }) => theme.body};
        color: white;
        overflow-x: hidden;
        transition: 0.5s;
        padding-top: 60px;
    `;

    const A = styled.a`
        color: ${({ theme }) => theme.logo};
    `;

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
                        <MenuLateral id="mySidenav" className="sidenav">
                            <A href="" className="closebtn" onClick={closeNav}>&times;</A>
                            <A href="">Configurações</A>
                        </MenuLateral>
                        <MenuLateralBotao onClick={openNav}>&#9776;</MenuLateralBotao>
                    </Fragment>
                </ThemeProvider>
            </>
        );
    }
    else if(props.funcao === "campanha"){
        return (
            <>
                <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                    <Fragment>
                        <GlobalTheme />
                        <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap" rel="stylesheet"></link>
                        <EstiloMenu>
                            <img alt="" onClick={()=>redirecionar("home")} className="logo" src={logo} />
                            <Title>bp</Title>
                            <div className="divDireita">
                                <img src={theme === "light" ? notificacaoPreto : notificacaoBranco} alt="" />
                                <img onClick={()=>redirecionar(sessionStorage.getItem("tipo"))} src={theme === "light" ? contaPreto : contaBranco} alt="" />
                                <img alt="" style={estilo} onClick={toggleTheme} className="tema" src={theme === "light" ? lua : sol} />
                            </div>
                        </EstiloMenu>
                        <MenuLateral id="mySidenav" className="sidenav">
                            <A href="" className="closebtn" onClick={closeNav}>&times;</A>
                            <A href="">Configurações</A>
                        </MenuLateral>
                        <MenuLateralBotao onClick={openNav}>&#9776;</MenuLateralBotao>
                    </Fragment>
                </ThemeProvider>
            </>
        );
    }
    else if(props.funcao==="menunota"){
        return (
            <>
                <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                    <Fragment>
                        <GlobalTheme />
                        <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap" rel="stylesheet"></link>
                        <EstiloMenu2>
                            <img alt="" onClick={()=>redirecionar("home")} className="logo" src={logo} />
                            <Title>bp</Title>
                            <div className="divDireita">
                                <img src={theme === "light" ? notificacaoPreto : notificacaoBranco} alt="" />
                                <img src={theme === "light" ? contaPreto : contaBranco} alt="" />
                                <img alt="" style={estilo} onClick={toggleTheme} className="tema" src={theme === "light" ? lua : sol} />
                            </div>
                        </EstiloMenu2>
                        <MenuLateral id="mySidenav" className="sidenav">
                            <A href="" className="closebtn" onClick={closeNav}>&times;</A>
                            <A href="">Configurações</A>
                        </MenuLateral>
                        <MenuLateralBotao onClick={openNav}>&#9776;</MenuLateralBotao>
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
                            <BarraPesquisa/>
                            <div className="divDireita">
                                <img alt="" onClick={toggleTheme} className="tema" src={theme === "light" ? lua : sol} />
                                <Botao onClick={()=>redirecionar("campanhas-doador")}>Campanhas</Botao>
                                <img src={theme === "light" ? notificacaoPreto : notificacaoBranco} alt="" />
                                <img src={theme === "light" ? contaPreto : contaBranco} alt="" />
                            </div>
                        </EstiloMenu>
                        <MenuLateral id="mySidenav" className="sidenav">
                            <A href="" className="closebtn" onClick={closeNav}>&times;</A>
                            <A href="">Configurações</A>
                        </MenuLateral>
                        <MenuLateralBotao onClick={openNav}>&#9776;</MenuLateralBotao>
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
function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/"+pagina;
}