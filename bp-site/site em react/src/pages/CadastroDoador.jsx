import Input from "../components/Input";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import api from "../api"

function CadastroDoador() {
    const [funcData, setFuncData] = useState({
        nome: "",
        email: "",
        senha: "",
        cpf: "",
        usuario: "",
        telefone: ""

    })

    async function enviar(e) {
        e.preventDefault();
        funcData.nome = document.getElementById("idNome").value;
        funcData.email = document.getElementById("idEmail").value;
        funcData.senha = document.getElementById("idSenha").value;
        funcData.cpf = document.getElementById("idCpf").value;
        funcData.usuario = document.getElementById("idUsuario").value;
        funcData.telefone = document.getElementById("idTelefone").value;
        console.log(funcData);
        api.post("/bp/doador/cadastroDoador", {
            nome: funcData.nome,
            email: funcData.email,
            senha: funcData.senha,
            cpf: funcData.cpf,
            usuario: funcData.usuario,
            telefone: funcData.telefone


        }).then((resposta) => {
            console.log("post ok", resposta);
        })
    }
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
    const InputStyle = styled.input`
        border-radius: 30px;
        height: 5vh;
        width: 60%;
        margin-left: 20%;
        text-align: center;
        background-color: transparent;
        border: ${({ theme }) => theme.bordaInput} 2px solid;
        color: ${({ theme }) => theme.letraInput};
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
                                <div className="inputDiv">
                                    <span className="nomeInput">Nome:</span><br/>
                                    <InputStyle type="text" id="idNome"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Email:</span><br/>
                                    <InputStyle type="text" id="idEmail"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Senha:</span><br/>
                                    <InputStyle type="password" id="idSenha"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">CPF:</span><br/>
                                    <InputStyle type="text" id="idCpf"/>
                                </div>
                            </div>
                            <div class="direita">
                            <div className="inputDiv">
                                    <span className="nomeInput">Usuário:</span><br/>
                                    <InputStyle type="text" id="idUsuario"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Telefone:</span><br/>
                                    <InputStyle type="text" id="idTelefone"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Confirmar senha:</span><br/>
                                    <InputStyle type="password" id="idConfirmarSenha"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Data Nascimento:</span><br/>
                                    <InputStyle type="date" id="idData"/>
                                </div>
                            </div>
                        </DivCadastro>
                        <div class="botao">
                            <Botao onClick={enviar} class="btnCadastrar">Cadastrar</Botao><br />
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
    window.location.href = "http://localhost:3000/" + pagina;
}