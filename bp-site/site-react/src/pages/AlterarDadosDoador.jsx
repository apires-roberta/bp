import Input from "../components/Input";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import apiLogin from "../apiLogin"
import apiCep from "../apiCep";
import CartaoPerfilDoador from "../components/CartaoPerfilDoador";


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
        width: 50%;
        margin-left: 10%;
        margin-top: 10%;
        height: 70vh;
        box-shadow: ${({ theme }) => theme.borda};
        color: ${({ theme }) => theme.azulClaro};
    `;
    const DivNome = styled.div`
        height: 10vh;
        margin-top:80%;
        margin-left:30%;
        float: left;
        font-size:15px;
        width: 10%;
    `;
    const DivFoto = styled.div`
        width: 180vh;
        height: 10vh;
        margin-top:12%;
        margin-left:10%;
        float: left;
        position: absolute
    `;

    const DivPerfilAlterar = styled.div`
        float: left;
        width: 30%;
        margin-left: 5%;
        margin-top: 10%;
        height: 70vh;
        box-shadow: ${({ theme }) => theme.borda};
        color: ${({ theme }) => theme.azulClaro};
        border-radius: 10px 10px 0px 0px;
    `;
    const Botao = styled.button`
        background-color: ${({ theme }) => theme.azulClaro};
        color: white;
        border: none;
        border-radius: 50px;
        box-shadow: ${({ theme }) => theme.borda};
        cursor:pointer;
        font-size: 32px;
        width: 40%;
        height: 7vh;
        margin-top: 5%;
        margin-bottom: 2%;
        margin-left: 19%;
    `;
    const DivImagem = styled.div`
        position: absolute;
        width: 30%;
    `;
    const Titulo = styled.h1`
        padding-top: 3%;
        margin-left: 11%
    `;
    const Span = styled.span`
        color:${({ theme }) => theme.logo};
    `;
    const fotoFundo = {
        height: "36vh",
        width: "100%",
        borderRadius: "10px 10px 0px 0px"
    }
    const fotoPerfil = {
        borderRadius: "50%",
        width: "11%",
        height: "20vh",
    }
    const nomeDoador = {
        fontSize: "32px",
        color: "{ theme }) => theme.letraInput}",
        align:"justify",
        float: "left",
        display: "inline-block",
        textAlign: "center",
        marginLeft: "50%"
       }
       const BioDoador = {
        fontSize: "20px",
        color: "{ theme }) => theme.letraInput}",
        float: "left",
        marginLeft: "13%"
       }

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
                            <Titulo>Alterar meus dados</Titulo>
                            <div className="esquerda">
                                <div className="inputDiv">
                                    <span className="nomeInput" placeholder="Maria das Couves" >Nome:</span><br/>
                                    <InputStyle type="text" id="idNome"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Email:</span><br/>
                                    <InputStyle type="text" id="idEmail"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">CEP:</span><br/>
                                    <InputStyle type="text" onBlur={teste} id="idCep"/>
                                </div>
                                <Input nome="Numero:" id="idNumero" tipo="text" />
                                <Input nome="Cidade:" id="idCidade" tipo="text" />
                                <div className="Botao">
                            <Botao onClick={enviar}>Atualizar</Botao><br/>
                        </div>
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
                                <Input nome="Rua:" id="idRua" tipo="text" />
                                <Input nome="Bairro:" id="idBairro" tipo="text" />
                                <Input nome="Estado:" id="idEstado" tipo="text" />
                                <div className="Botao">
                                
                        </div>
                            </div>
                        </DivCadastro>
                        <DivPerfilAlterar>
                            <DivImagem>
                                <img style={fotoFundo} src="https://f8n-production.s3.amazonaws.com/creators/profile/rf7rdju95-237-2376520-twitter-header-wallpapers-minimalist-dual-monitor-wallpaper-4k-jpg-cqlv2j.jpg" alt="" />
                            </DivImagem>
                            <DivFoto>
                            <img alt="" style={fotoPerfil} src="https://images03.brasildefato.com.br/d753690c552a7a06b95d7b17ea689f06.jpeg" />
                            </DivFoto>
                            <DivNome>
                                <span style={nomeDoador}>Maria Aparecida</span><br></br>
                            </DivNome>
                            <span style={BioDoador}>"Sou mãe e casada, gosto muito de poder ajudar as pessoas da forma que posso!"</span>

                        </DivPerfilAlterar>

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