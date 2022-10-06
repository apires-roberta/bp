import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import React, { Fragment, useState, useEffect } from "react";
import CartaoPerfilOng from "../components/CartaoPerfilOng";
import Rodape from "../components/Rodape";
import CartaoPerfilDoador from "../components/CartaoPerfilDoador";
function Perfil(){
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

    if(sessionStorage.getItem('tipo')==="PerfilDoador"){
        return(
            <>
            <Menu funcaoDark={toggleTheme}/>
                <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                    <Fragment>
                        <GlobalTheme />
                        <CartaoPerfilDoador/>
                        <Rodape/>
                    </Fragment>
                </ThemeProvider>
            </>
        );
    }
    else if(sessionStorage.getItem('tipo')==="PerfilOng"){
        return(
            <>
            <Menu funcaoDark={toggleTheme}/>
                <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                    <Fragment>
                        <GlobalTheme />
                        <CartaoPerfilOng/>
                        <Rodape/>
                    </Fragment>
                </ThemeProvider>
            </>
        );
    }
    else{
        redirecionar("login")
    }
    
}

export default Perfil;

function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/" + pagina;
  }