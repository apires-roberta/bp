import CardCampanhaDoador from "../components/CardCampanhaDoador";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import React, { Fragment, useState, useEffect } from "react";
import styled from "styled-components";
import apiCampanha from "../apiCampanha";

function CampanhasDoador() {

  const [theme, setTheme] = useState("dark");

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
      margin-top: 5%;
      margin-left: 8%;
    `;

  const [campanha, setcampanha] = useState([]);

  useEffect(() => {
    apiCampanha.get("/disponiveis/").then((resposta) => {
      console.log(resposta.data)
      setcampanha(resposta.data)
    })
  }, [])
  
  return (
    <>
      <Menu funcaoDark={toggleTheme} funcao="campanha" />
      <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
        <Fragment>
          <GlobalTheme />
          <DivInfo>
            {
              campanha.map((item) => (
                <CardCampanhaDoador id={item.fkOng}
                descCampanha={item.descCampanha}
                valorCampanha={item.valorNecessario} />
              ))
            }
          </DivInfo>
        </Fragment>
      </ThemeProvider>
    </>
  );
}

export default CampanhasDoador;
