import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import React, { Fragment, useState, useEffect } from "react";
import CartaoPerfilDoador from "../components/CartaoPerfilDoador";
import Rodape from "../components/Rodape";

function PerfilDoador(){
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

export default PerfilDoador;