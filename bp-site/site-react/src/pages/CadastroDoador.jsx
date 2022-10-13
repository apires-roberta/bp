import Input from "../components/Input";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import apiLogin from "../apiLogin"
import apiCep from "../apiCep";
import Rodape from "../components/Rodape";

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
        funcData.cep = document.getElementById("idCep").value;
        funcData.numeroResidencia = document.getElementById("idNumero").value;
        funcData.dataNascimento = document.getElementById("idData").value;
        console.log(funcData);
        apiLogin.post("/bp/doador/cadastroDoador", {
            nome: funcData.nome,
            email: funcData.email,
            senha: funcData.senha,
            cpf: funcData.cpf,
            usuario: funcData.usuario,
            telefone: funcData.telefone,
            cep: funcData.cep,
            numeroResidencia: funcData.numeroResidencia,
            dataNascimento: funcData.dataNascimento


        }).then((resposta) => {
            console.log("post ok", resposta);
            redirecionar("login")
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
        height: 90vh;
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
                        <DivCadastro>
                            <Titulo>Cadastre-se como doador:</Titulo>
                            <div className="esquerda">
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
                                <div className="inputDiv">
                                    <span className="nomeInput">CEP:</span><br/>
                                    <InputStyle type="text" onBlur={teste} id="idCep"/>
                                </div>
                                <Input nome="Numero:" id="idNumero" tipo="text" />
                                <Input nome="Cidade:" id="idCidade" tipo="text" />
                            </div>
                            <div className="direita">
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
                                <Input nome="Rua:" id="idRua" tipo="text" />
                                <Input nome="Bairro:" id="idBairro" tipo="text" />
                                <Input nome="Estado:" id="idEstado" tipo="text" />
                            </div>
                        </DivCadastro>
                        <div className="botao">
                            <Botao onClick={enviar} className="btnCadastrar">Cadastrar</Botao><br />
                            <Span>Para se cadastrar como Ong <a onClick={() => redirecionar("cadastro-ong")}>clique aqui!</a></Span>
                        </div>
                    <Rodape/>
                </Fragment>
            </ThemeProvider>

        </>
    );
}

export default CadastroDoador;

function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/" + pagina;
}

function teste(){
    var cep = document.getElementById("idCep").value;
    apiCep.get(`/${cep}/json`).then((resposta) => {
        console.log(resposta)
        document.getElementById("idCidade").value=resposta.data.localidade;
        document.getElementById("idRua").value=resposta.data.logradouro;
        document.getElementById("idBairro").value=resposta.data.bairro;
        document.getElementById("idEstado").value=resposta.data.uf;
    })
}