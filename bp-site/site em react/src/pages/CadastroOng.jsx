import Input from "../components/Input";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import api from "../api"

function CadastroOng() {
    const [funcData, setFuncData] = useState({
        nome: "",
        email: "",
        senha: "",
        cnpj: "",
        usuario: "",
        telefone: ""

    })

    async function enviar(e) {
        if (document.getElementById("idSenha").value === document.getElementById("idConfirmarSenha").value) {
            e.preventDefault();
            funcData.nome = document.getElementById("idNome").value;
            funcData.email = document.getElementById("idEmail").value;
            funcData.senha = document.getElementById("idSenha").value;
            funcData.cnpj = document.getElementById("idCnpj").value;
            funcData.usuario = document.getElementById("idUsuario").value;
            funcData.telefone = document.getElementById("idTelefone").value;
            console.log(funcData);
            api.post("/bp/ong/cadastroOng", {
                nome: funcData.nome,
                email: funcData.email,
                senha: funcData.senha,
                cnpj: funcData.cnpj,
                usuario: funcData.usuario,
                telefone: funcData.telefone


            }).then((resposta) => {
                console.log("post ok", resposta);
                redirecionar("login")
            })
        }
        else{
            document.getElementById("idConfirmarSenha").style="border: 2px solid red";
        }
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
        height: 80vh;
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
                            <Titulo>Cadastre-se como ong:</Titulo>
                            <div class="esquerda">
                                <Input nome="Nome:" id="idNome" tipo="text" />
                                <Input nome="Email:" id="idEmail" tipo="text" />
                                <Input nome="Senha:" id="idSenha" tipo="password" />
                                <Input nome="CNPJ:" id="idCnpj" tipo="text" />
                                <Input nome="Rua:" id="idRua" tipo="text" />
                                <Input nome="Bairro:" id="idBairro" tipo="text" />
                            </div>
                            <div class="direita">
                                <Input nome="Usuário:" id="idUsuario" tipo="text" />
                                <Input nome="Telefone:" id="idTelefone" tipo="text" />
                                <Input nome="Confirmar senha:" id="idConfirmarSenha" tipo="password" />
                                <Input nome="CEP:" id="idCep" tipo="text" />
                                <Input nome="Numero:" id="idNumero" tipo="text" />
                                <Input nome="Cidade:" id="idCidade" tipo="text" />
                            </div>
                        </DivCadastro>
                        <div class="botao">
                            <Botao onClick={enviar} class="btnCadastrar">Cadastrar</Botao><br />
                            <Span>Para se cadastrar como Doador <a onClick={() => redirecionar("cadastro-doador")}>clique aqui!</a></Span>
                        </div>
                    </div>
                </Fragment>
            </ThemeProvider>

        </>
    );
}

export default CadastroOng;

function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/" + pagina;
}