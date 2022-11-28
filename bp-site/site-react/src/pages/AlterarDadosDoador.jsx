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
import contaBranco from "../img/usuarioBranco.png";
import contaPreto from "../img/usuarioPreto.png";
import capaBranco from "../img/fundoBranco.png";
import { Upload } from "@aws-sdk/lib-storage";
import { S3 } from "@aws-sdk/client-s3";
import DadosS3 from "../DadosS3";

function AlterarDadosOng() {
function teste3(){
    var photo = document.getElementById('idImagemPerfil');
    var file = document.getElementById('arquivo2');

    file.addEventListener('change', () => {

        if (file.files.length <= 0) {
            return;
        }

        let reader = new FileReader();

        reader.onload = () => {
            photo.src = reader.result;
        }

        reader.readAsDataURL(file.files[0]);
    });
    console.log("foto", photo)
}

function upload (id, nome, atual){
    var nomeFoto = nome.replaceAll(" ","_")
    var fileInput = document.getElementById(id);
    if(fileInput.files.length>0){
    var file = fileInput.files[0];

    const target = {Bucket:"s3-lab04-juan", Key:nomeFoto, Body:file}//s3-project-bp
    const creds = {accessKeyId:DadosS3.chave_acesso, secretAccessKey:DadosS3.chave_secreta, sessionToken:DadosS3.session_token}

    try {
      const parallelUploads3 = new Upload({
        client: new S3({region:"us-east-1", credentials:creds}),
        leavePartsOnError: false, // optional manually handle dropped parts
        params: target,
      });
    
      parallelUploads3.on("httpUploadProgress", (progress) => {
        console.log(progress);
      });
    
      parallelUploads3.done();
    } catch (e) {
      return atual;
    } finally {
        return "https://s3-lab04-juan.s3.amazonaws.com/"+nomeFoto;
    }
    }
    else{
        return atual;
    }
}

function teste2(){
    var photo = document.getElementById('idImagemFundo');
    var file = document.getElementById('teste');

    file.addEventListener('change', () => {

        if (file.files.length <= 0) {
            return;
        }

        let reader = new FileReader();

        reader.onload = () => {
            photo.src = reader.result;
        }

        reader.readAsDataURL(file.files[0]);
    });
    console.log("foto", photo)
}

    var [perfil, setperfil] = useState([]);
    var [endereco, setendereco] = useState([]);
    useEffect(() => {
        apiLogin.get(`/bp/doador/${sessionStorage.getItem("idDoador")}`).then((resposta) => {
          if (resposta.status === 200) {
            setperfil(resposta.data)
            console.log("perfil",resposta.data)
            apiCep.get(`/${"09560065"}/json`).then((resposta) => {
                console.log("cep", resposta.data)
                setendereco(resposta.data)
            })
          }
        })
      }, [])
    const [funcData, setFuncData] = useState({
        nome: "",
        email: "",
        bio: "",
        usuario: "",
        cep: "",
        numero: "",
        telefone: ""

    })

    async function enviar(e) {
        e.preventDefault();
        funcData.nome = document.getElementById("idNome").value;
        funcData.email = document.getElementById("idEmail").value;
        funcData.usuario = document.getElementById("idUsuario").value;
        funcData.telefone = document.getElementById("idTelefone").value;
        funcData.cep = document.getElementById("idCep").value;
        funcData.numero = document.getElementById("idNumero").value;
        funcData.bio = document.getElementById("idBio").value;
        funcData.fotoPerfil = upload("arquivo2", funcData.nome+"Perfil", perfil.fotoPerfil)
        funcData.fotoFundo = upload("teste", funcData.nome+"Capa", perfil.fotoCapa)
        console.log(funcData);
        apiLogin.patch(`/bp/doador/novas-infos/${sessionStorage.getItem("idDoador")}`, {
            nome: funcData.nome,
            email: funcData.email,
            usuario: funcData.usuario,
            telefone: funcData.telefone,
            cep: funcData.cep,
            numero: funcData.numero,
            bio: funcData.bio,
            fotoPerfil: funcData.fotoPerfil,
            fotoCapa: funcData.fotoFundo
        }).then((resposta) => {
            console.log("post ok", resposta);
            redirecionar("perfil")
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
        width: 52%;
        margin-left: 5%;
        margin-top: 10%;
        height: 73vh;
        box-shadow: ${({ theme }) => theme.borda};
        color: ${({ theme }) => theme.azulClaro};
    `;
    const DivNome = styled.div`
        height: 10vh;
        margin-top:60%;
        font-size:15px;
        text-align: center;
    `;
    const DivBio = styled.div`
        height: 10vh;
        font-size:15px;
        text-align: center;
    `;
    const DivFoto = styled.div`
        width: 10%;
        margin-top:8%;
        margin-left:12.5%;
        position: absolute;
    `;

    const DivPerfilAlterar = styled.div`
        float: left;
        width: 35%;
        margin-left: 3%;
        margin-top: 10%;
        height: 73vh;
        box-shadow: ${({ theme }) => theme.borda};
        color: ${({ theme }) => theme.azulClaro};
        border-radius: 10px 10px 10px 10px;
    `;
    const Botao = styled.button`
    background-color: ${({ theme }) => theme.azulClaro};
    color: white;
    border: none;
    border-radius: 50px;
    box-shadow: ${({ theme }) => theme.borda};
    font-size: 32px;
    width: 62%;
    height: 7vh;
    margin-top: 6%;
    margin-bottom: 2%;
    margin-left: 19%;
    `;
    const DivImagem = styled.div`
        position: absolute;
        width: 35%;
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
        width: "100%",
        height: "20vh",
    }
    const nomeOng = {
        fontSize: "32px",
        color: "{ theme }) => theme.letraInput}",
        align:"justify",
        display: "inline-block",
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
    
    const estiloInput ={
        display: "none"
    }

    const estiloLabel ={
        backgroundColor: "red"
    }

    function pegarBio(){
        document.getElementById("spanBio").innerHTML=document.getElementById("idBio").value
    }

    return (
        <>
            <Menu funcaoDark={toggleTheme} funcao="cadastro" />
            <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
                        <DivCadastro>
                            <Titulo>Alterar dados da Ong</Titulo>
                            <div className="esquerda">
                                <div className="inputDiv">
                                    <span className="nomeInput" placeholder="Maria das Couves" >Nome:</span><br/>
                                    <InputStyle defaultValue={perfil.nome} type="text" id="idNome"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Email:</span><br/>
                                    <InputStyle defaultValue={perfil.email} type="text" id="idEmail"/>
                                
                                <div className="inputDiv">
                                    <span className="nomeInput">Bio:</span><br/>
                                        <InputStyle defaultValue={perfil.bio == null ? "":perfil.bio} onChange={pegarBio} id="idBio" type="text" />
                                    </div>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Rua:</span><br/>
                                    <InputStyle defaultValue={endereco.logradouro} disabled={true} type="text" id="idRua"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Numero:</span><br/>
                                    <InputStyle defaultValue={perfil.numero} type="text" onBlur={teste} id="idNumero"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Cidade:</span><br/>
                                    <InputStyle defaultValue={endereco.localidade} type="text" disabled={true} id="idCidade"/>
                                </div>
                            </div>
                            <div className="direita">
                                <div className="inputDiv">
                                    <span className="nomeInput">Usuário:</span><br/>
                                    <InputStyle defaultValue={perfil.usuario} type="text" id="idUsuario"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Telefone:</span><br/>
                                    <InputStyle defaultValue={perfil.telefone} type="text" id="idTelefone"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">CEP:</span><br/>
                                    <InputStyle defaultValue={perfil.cep} type="text" onBlur={teste} id="idCep"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Bairro:</span><br/>
                                    <InputStyle defaultValue={endereco.bairro} disabled={true} type="text" id="idBairro"/>
                                </div>
                                <div className="inputDiv">
                                    <span className="nomeInput">Estado:</span><br/>
                                    <InputStyle disabled={true} defaultValue={endereco.uf} type="text" id="idEstado"/>
                                </div>
                                <div className="Botao">
                            <Botao onClick={enviar}>Atualizar</Botao><br/>
                        </div>
                            </div>
                        </DivCadastro>
                        <DivPerfilAlterar>
                            <DivImagem>
                                <label for="teste">
                                <img onClick={teste2} id="idImagemFundo" style={fotoFundo} src={perfil.fotoCapa == null ? capaBranco : perfil.fotoCapa} alt="" />
                                </label>
                                <input style={estiloInput} type="file" className="foto" name="teste" id="teste" accept="image/*"></input>
                            </DivImagem>
                            <DivFoto>
                                <label forhtml="arquivo2"> 
                                    <img onClick={teste3} id="idImagemPerfil" alt="" style={fotoPerfil} src={perfil.fotoPerfil == null ? theme === "light" ? contaPreto : contaBranco : perfil.fotoPerfil} />
                                    <input style={estiloInput} type="file" className="foto" name="arquivo2" id="arquivo2" accept="image/*"></input>
                                </label>
                            </DivFoto>
                            <DivNome>
                                <span style={nomeOng}>{perfil.nome}</span><br></br>
                            </DivNome>
                            <DivBio>
                            <span id="spanBio">{perfil.bio}</span>
                            </DivBio>

                        </DivPerfilAlterar>

                </Fragment>
                
            </ThemeProvider>

        </>
    );
}

export default AlterarDadosOng;

function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/" + pagina;
}

function carregarGrafico(){
    var elemento = document.getElementById()
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

/*

                            
                            */