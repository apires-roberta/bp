import Input from "../components/Input";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";


function CadastroDoador() {
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
    const DivCadastro = styled.div`
        float: left;
        width: 80%;
        margin-left: 10%;
        margin-top: 10%;
        height: 55vh;
        box-shadow: ${({ theme }) => theme.borda};
        color: ${({ theme }) => theme.azulClaro};
    `;
    const Botao = styled.button`
        text-align: center;
        background-color: ${({ theme }) => theme.azulClaro};
        color: white;
        border: none;
        border-radius: 50px;
        box-shadow: ${({ theme }) => theme.borda};
        cursor:pointer;
        font-size: 32px;
        width: 40%;
        height: 10vh;
        margin-top: 1%;
        margin-bottom: 2%;
    `;
    const Titulo = styled.h1`
        padding-top: 1%;
        text-align: center;
    `;
    const Span = styled.span`
        color:${({ theme }) => theme.logo};
    `;
    return (
        <>
            <Menu funcaoDark={toggleTheme} funcao="cadastro" />
            <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
                    <div className="cadastro">
                        <DivCadastro>
                            <Titulo>Cadastre-se como doador:</Titulo>
                            <div class="esquerda">
                                <Input nome="Nome:" id="idNome" tipo="text" />
                                <Input nome="Email:" id="idEmail" tipo="text" />
                                <Input nome="Senha:" id="idSenha" tipo="password" />
                                <Input nome="CPF:" id="idCpf" tipo="text" />
                            </div>
                            <div class="direita">
                                <Input nome="Usuário:" id="idUsuario" tipo="text" />
                                <Input nome="Telefone:" id="idTelefone" tipo="text" />
                                <Input nome="Confirmar senha:" id="idConfirmarSenha" tipo="password" />
                                <Input nome="Data Nascimento:" id="idData" tipo="date" />
                            </div>
                        </DivCadastro>
                        <div class="botao">
                            <Botao onClick={() => redirecionar("login")} class="btnCadastrar">Cadastrar</Botao><br />
                            <Span>Para se cadastrar como Ong <a onClick={() => redirecionar("cadastro-ong")}>clique aqui!</a></Span>
                        </div>
                    </div>
                </Fragment>
            </ThemeProvider>

        </>
    );
}

export default CadastroDoador;

function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/"+pagina;
  }