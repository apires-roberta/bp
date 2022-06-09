import CardCampanhaOng from "../components/CardCampanhaOng";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import React, { Fragment, useState, useEffect } from "react";
import styled from "styled-components";
import BasicModal from "../components/Modal";
import apiCampanha from "../apiCampanha";
import apiLogin from "../apiLogin";

function CampanhasOng() {
  const [theme, setTheme] = useState("light");
  const [nomeOng, setNomeOng] = useState("");

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

    const [campanha, setcampanha] = useState([]);

  useEffect(() => {
    apiCampanha.get(`/${sessionStorage.getItem("idOng")}`).then((resposta) => {
      console.log(resposta)
      setcampanha(resposta.data)
    })
  }, [])

  useEffect(() => {
    apiLogin.get(`/bp/ong/${sessionStorage.getItem("idOng")}`).then((resposta) => {
      setNomeOng(resposta.data.nome)
    })
  }, [])
  console.log(nomeOng)

  return (
    <>
      <Menu funcaoDark={toggleTheme} funcao = "campanha"/>
      <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
        <Fragment>
          <GlobalTheme />
          <DivInfo>{
            campanha.map((item) => (
              <CardCampanhaOng nome={nomeOng}
              descCampanha={item.descCampanha}
              valorCampanha={item.valorNecessario} 
              id={item.idCampanha}/>
              
            ))
            }
          
            <BasicModal/>
          </DivInfo>
        </Fragment>
      </ThemeProvider>
      
    </>
  );
}

export default CampanhasOng;

