import Input from "../components/Input";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import apiLogin from "../apiLogin"
import CardDados from "../components/CardDados";
import { GraficoDash } from "../components/GraficoDash";
import MapChart from "../components/MapChart";


function DashMapa() {
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
    
    return (
        <>
            <Menu funcaoDark={toggleTheme} funcao="cadastro" />
            <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
                    <MapChart fundo={theme === "light" ? "white" : "#111111"} hover={theme === "light" ? "#0070DC" : "#01396F"} linha={theme === "light" ? "#111111" : "white"} click={theme === "light" ? "#01396F" : "#0070DC"} />
                    
                </Fragment>
            </ThemeProvider>
        </>
    );
}

export default DashMapa;