import CardCampanhaOng from "../components/CardCampanhaOng";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import React, { Fragment, useState, useEffect } from "react";
import styled from "styled-components";
import BasicModal from "../components/Modal";

function CampanhasOng() {
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

    const DivInfo = styled.div`
      width: 80%;
      margin-top: 10%;
      margin-left: 8%;
    `;

  return (
    <>
      <Menu funcaoDark={toggleTheme} funcao = "campanha"/>
      <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
        <Fragment>
          <GlobalTheme />
          <DivInfo>
            <CardCampanhaOng nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00} />
            <CardCampanhaOng nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00} />
            <CardCampanhaOng nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00} />
            <CardCampanhaOng nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00} />
            <CardCampanhaOng nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00} />
            <CardCampanhaOng nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00} />
            <CardCampanhaOng nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00} />
            <CardCampanhaOng nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00} />
            <CardCampanhaOng nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00} />
            <CardCampanhaOng nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00} />
            <BasicModal/>
          </DivInfo>
        </Fragment>
      </ThemeProvider>
      
    </>
  );
}

export default CampanhasOng;

