import Input from "../components/Input";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";

function Login() {
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
    const DivLogin = styled.div`
        width: 40%;
        float: left;
        margin-left: 30%;
        margin-top: 5%;
        color: #01396F;
        height: 60vh;
        box-shadow: ${({ theme }) => theme.borda};
        color: ${({ theme }) => theme.azulClaro};
        position: fixed;
        top: 8%;
    `;
    const Titulo = styled.h1`
        text-align: center;
        margin-top: 10%;
    `;
    const Botao = styled.button`
        text-align: center;
        background-color: ${({ theme }) => theme.azulClaro};
        color: white;
        border: none;
        border-radius: 50px;
        box-shadow: ${({ theme }) => theme.borda};
        cursor:pointer;
        font-size: 20px;
        width: 50%;
        height: 7vh;
        margin-top: 5%;
        margin-bottom: 5%;
        margin-left: 25%;
    `;
    const aCentro={
        marginLeft: "33%"
    };

    const BotaoPequeno = styled.button`
        text-align: center;
        background-color: ${({ theme }) => theme.azulClaro};
        color: white;
        border: none;
        border-radius: 50px;
        box-shadow: ${({ theme }) => theme.borda};
        cursor:pointer;
        font-size: 16px;
        width: 10%;
        height: 5vh;
        margin-top: 1%;
        margin-bottom: 5%;
    `;

    const Texto = styled.span`
        color: ${({ theme }) => theme.logo}
    `;
    return (
        <>
            <Menu funcaoDark={toggleTheme} funcao="cadastro" />
            <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
            <DivLogin>
                <Titulo>Login</Titulo>
                <Input nome="Email:" id="idEmail" tipo="text"/>
                <Input nome="Senha:" id="idSenha" tipo="password"/>
                <Botao onClick={logar} className="btnLogar">Entrar</Botao><br/>
                <a style={aCentro}>Esqueceu a senha?</a>
            </DivLogin>
            <div className="divCadastrar">
                <Texto>Cadastre-se como:</Texto><br/>
                <BotaoPequeno onClick={() => redirecionar("cadastro-ong")}>Ong</BotaoPequeno>
                <BotaoPequeno onClick={() => redirecionar("cadastro-doador")} className="btnDireita">Doador</BotaoPequeno>
            </div>
            </Fragment>
            </ThemeProvider>
        </>
    );
  }
  
  export default Login;

  function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/"+pagina;
  }

  function logar(){
      var usuario = "ong";
      usuario === "ong" ? redirecionar("campanhas-ong") : redirecionar("campanhas-doador")
  }