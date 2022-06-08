import { Grafico } from "../components/Grafico";
import Menu from "../components/Menu";
import CardDados from "../components/CardDados";
import BotaoDoacao from "../components/BotaoDoacao";
import send from "../img/send.png"
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
function Doacao() {
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
  const DivValor = styled.div`
      margin-top: 10%;
      float: left;
      color: ${({ theme }) => theme.logo};
    `;
  const InputValor = styled.input`
      float: left;
      margin-left: 1%;
      border: none;
      border-bottom: 2px solid ${({ theme }) => theme.logo};
      margin-top: 3%;
      width: 80%;
      background-color: ${({ theme }) => theme.body};
      color:  ${({ theme }) => theme.logo};
    `;
  return (
    <>
      <Menu funcaoDark={toggleTheme} />
      <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
        <Fragment>
          <GlobalTheme />
          <CardDados nome="Juan" descricao="123456789 123456789 123456789 123456789 123456789 123456789 " valorCampanha="1000,00" />
          <div className="grafico">
            <Grafico valorDesejado={100} valorAtual={20} />
          </div>
          <div className="botoesDoacoes">
            <BotaoDoacao valor={10} />
            <BotaoDoacao valor={20} />
            <BotaoDoacao valor={30} />
            <BotaoDoacao valor={40} />
            <BotaoDoacao valor={50} />
            <div className="botaoOutro">
              <DivValor className="outroValor">
                <span>Outro valor:</span><br />
                <p>R$</p><InputValor id="valores" type="text" />
              </DivValor>
              <button><img className="enviarValor" src={send} /></button>
            </div>
          </div>
        </Fragment>
      </ThemeProvider>
    </>
  );
}

export default Doacao;