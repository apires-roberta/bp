import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import React, { Fragment, useState, useEffect } from "react";
import Rodape from "../components/Rodape";
import AlterarDadosDoador from "../components/AlterarDadosDoador";
import AlterarDadosOng from "../components/AlterarDadosOng";
import ip from '../ip';
function Alterar(){
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
                        <AlterarDadosDoador/>
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
                        <AlterarDadosOng/>
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

export default Alterar;

function redirecionar(pagina) {
    window.location.href = `http://${ip}/` + pagina;
  }