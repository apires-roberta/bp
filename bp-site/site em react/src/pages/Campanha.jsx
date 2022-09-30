import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import React, { Fragment, useState, useEffect } from "react";
import Rodape from "../components/Rodape";
import CampanhasDoador from "../components/CampanhasDoador";
import CampanhasOng from "../components/CampanhasOng";

function Campanha(){
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
                        <CampanhasDoador/>
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
                        <CampanhasOng/>
                        <Rodape/>
                    </Fragment>
                </ThemeProvider>
            </>
        );
    }
    else{
        return(
            <>
            <Menu funcaoDark={toggleTheme} funcao="cadastro"/>
                <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                    <Fragment>
                        <GlobalTheme />
                        <div>
                        <CampanhasDoador/>
                        </div>
                        
                        <Rodape/>
                    </Fragment>
                </ThemeProvider>
            </>
        );
    }
    
}

export default Campanha;