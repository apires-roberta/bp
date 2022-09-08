import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import CartaoPerfilOng from "../components/CartaoPerfilDoador";
import CartaoPerfilDoador from "../components/CartaoPerfilDoador";
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
                    <CartaoPerfilDoador>
                    </CartaoPerfilDoador>
                </Fragment>
            </ThemeProvider>
        </>
    );
}

export default PerfilDoador;