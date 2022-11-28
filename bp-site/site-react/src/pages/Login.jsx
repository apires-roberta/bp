import Input from "../components/Input";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import apiLogin from "../apiLogin"
import Rodape from "../components/Rodape";
import ip from '../ip';

const Swal = require('sweetalert2')

function Login() {
    const [funcData, setFuncData] = useState({
        email: "",
        senha: ""
    })
    async function enviar() {
        funcData.email = document.getElementById("idEmail").value;
        funcData.senha = document.getElementById("idSenha").value;
        console.log(funcData);
        if(funcData.email=="adm@bp.com" && funcData.senha=="Senha123"){
            Swal.fire(
                'Login efetuado',
                'Vamos lá!',
                'success'
              );
            redirecionar("DashboardMapa")
        }
        else{
        apiLogin.post("/bp/doador/login", {
            email: funcData.email,
            senha: funcData.senha
        }).then((resposta) => {
            Swal.fire(
                'Login efetuado',
                'Vamos lá!',
                'success'
              );
            console.log("post ok", resposta);
            sessionStorage.setItem("tipo", "PerfilDoador");
            sessionStorage.setItem("idDoador", resposta.data)
            redirecionar("Perfil");
        }).catch((resposta) =>{
            apiLogin.post("/bp/ong/login", {
                email: funcData.email,
                senha: funcData.senha
            }).then((resposta) => {
                Swal.fire(
                    'Login efetuado',
                    'Vamos lá!',
                    'success'
                  );
                console.log("post ok", resposta);
                sessionStorage.setItem("idOng", resposta.data)
                sessionStorage.setItem("tipo", "PerfilOng");
                redirecionar("Perfil");
            }).catch((resposta) =>{
                document.getElementById("idEmail").style="border: 2px solid red";
                document.getElementById("idSenha").style="border: 2px solid red";
            })
        })
    }
    }

    document.addEventListener('keydown', function (event) {
        if (event.code === 'Enter'){
            enviar()
        }
    });

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
        margin-left: 30%;
        margin-top: 10%;
        padding-top: 1.5%;
        color: #01396F;
        height: 60vh;
        box-shadow: ${({ theme }) => theme.borda};
        color: ${({ theme }) => theme.azulClaro};
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
        font-size: 20px;
    `;

    const Texto = styled.span`
        color: ${({ theme }) => theme.logo}
    `;

    const estilo = {
        textAlign: "center",
        fontSize: "20px",
        marginTop: "2%"
    }

    const estiloButao={
        marginLeft: "5%"
    }
    if(sessionStorage.getItem("tipo")===""||sessionStorage.getItem("tipo")===null)
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
                <Botao onClick={enviar} className="btnLogar">Entrar</Botao><br/>
                <a style={aCentro}>Esqueceu a senha?</a>
            </DivLogin>
            <div style={estilo}>
                <Texto>Cadastre-se como:</Texto><br/>
                <BotaoPequeno onClick={() => redirecionar("cadastro-ong")}>Ong</BotaoPequeno>
                <BotaoPequeno style={estiloButao} onClick={() => redirecionar("cadastro-doador")} className="btnDireita">Doador</BotaoPequeno>
            </div>
            <Rodape/>
            </Fragment>
            </ThemeProvider>
        </>
    );
    else{
        redirecionar("Perfil")
    }
  }
  
  export default Login;

  function redirecionar(pagina) {
    window.location.href = "http://${ip}/"+pagina;
  }