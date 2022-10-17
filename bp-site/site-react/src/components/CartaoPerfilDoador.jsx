import styled from "styled-components";
import apiLogin from "../apiLogin"
import React, { useState, useEffect } from "react";
import apiCampanha from "../apiCampanha";
import contaBranco from "../img/usuarioBranco.png";
import contaPreto from "../img/usuarioPreto.png";
import capaBranco from "../img/fundoBranco.png";
function CartaoPerfilDoador() {
    const [perfil, setperfil] = useState([]);
    const [qtdColaboracao, setColaboracao] = useState();
    useEffect(() => {
        apiLogin.get(`/bp/doador/${sessionStorage.getItem("idDoador")}`).then((resposta) => {
          if (resposta.status === 200) {
            setperfil(resposta.data)
            console.log(resposta.data)
          }
        })
      }, [])
      apiCampanha.get(`/doacao/quantidade/doador/${sessionStorage.getItem("idDoador")}`).then((resposta) => {
        if(resposta.status===200){
            console.log(resposta.data)
            setColaboracao(resposta.data)
        }
      })
    const Cartao = styled.div`
        height:90vh;
        margin-top:8%;
        width:70%;
        border-radius:10px;
        box-shadow: ${({ theme }) => theme.bordaFeed};
        background-color: ${({ theme }) => theme.body};
        color: ${({ theme }) => theme.logo};
        margin-left:15%;
        position:relative;
    `;
    const DivLogin = styled.div`
        width: 25%;
        float: left;
        margin-left: 10%;
        border-radius:10px;
        margin-top: 15%;
        background-color:${({ theme }) => theme.body};
        color: #01396F;
        height: 55vh;
        position:relative;
        z-index:98;
        box-shadow: ${({ theme }) => theme.bordaFeed};
        color: ${({ theme }) => theme.azulClaro};
    `;
    const Divtext = styled.div`
        height: 10vh;
        margin-top:10%;
        float: left;
        font-size:25px;
        text-align: center;
        width: 100%;
    `;
    const DivImagem = styled.div`
        position: absolute;
        width: 100%;
    `;
    const fotoFundo = {
        height: "40vh",
        width: "100%",
        borderRadius: "10px 10px 0px 0px"
    }
    const fotoPerfil = {
        borderRadius: "3%",
        width: "90%",
        marginLeft: "5%"
    }
    const nomeOng = {
        fontSize: "32px",
        color: "white",
    }
    const slogan = {
        fontSize: "24px",
        color: "white",
    }
    const alinhaBtn = {
        marginLeft: "42.5%",
        width: "50%",
        paddingTop:"35%",

    }
    const titulosCartao = {
        fontSize: "22px"
    }
    const imgMedalhas = {
        width: "32%"
    }
    const estilo={
        float: "right",
        zIndex: "3",
        position: "relative",
        width: "50%",
        marginTop:"20%",
        marginRight:"5%"
    }
    const localTheme = window.localStorage.getItem("theme");
    if(perfil!='')
    return (
        <>
            <Cartao>
                <DivImagem>
                    <img style={fotoFundo} src={perfil.fotoCapa==null?capaBranco:perfil.fotoCapa} alt="" />
                </DivImagem>
                <div style={estilo}>
                    <span style={nomeOng}>{perfil.nome}<br /></span>
                    <span id="idIdade" style={slogan}>{idade(perfil.dataNascimento)} anos</span>
                </div>
                <DivLogin>
                    <img alt="" style={fotoPerfil} src={perfil.fotoPerfil==null?localTheme === "light" ? contaPreto : contaBranco:perfil.fotoPerfil} />
                    <Divtext>
                        <span>Colaborações:<br></br></span><span>{qtdColaboracao}</span><br /><br />
                        <span>Pontuação:</span><br></br><span>{perfil.pontuacao}</span>
                    </Divtext>
                </DivLogin>
                <div style={alinhaBtn}>
                    <span style={titulosCartao}> Bio:</span><br /><br /><span>"{perfil.bio}"</span><br /><br /><br />
                    <span style={titulosCartao}> Medalhas:</span><br /><br />
                    <img alt="" style={imgMedalhas} src="https://cdn-icons-png.flaticon.com/512/2583/2583381.png" /><img alt="" style={imgMedalhas} src="https://cdn-icons-png.flaticon.com/512/2583/2583350.png" /> <img alt="" style={imgMedalhas} src="https://cdn-icons-png.flaticon.com/512/2583/2583448.png" />
                </div>
            </Cartao>
        </>
    );
}

function idade(data) {
    var ano_aniversario, mes_aniversario, dia_aniversario;
    data=data.split("-")
    ano_aniversario=data[0]
    mes_aniversario=data[1]
    dia_aniversario=data[2]
    var d = new Date,
        ano_atual = d.getFullYear(),
        mes_atual = d.getMonth() + 1,
        dia_atual = d.getDate(),

        quantos_anos = ano_atual - ano_aniversario;

    if (mes_atual < mes_aniversario || mes_atual == mes_aniversario && dia_atual < dia_aniversario) {
        quantos_anos--;
    }

    return quantos_anos < 0 ? 0 : quantos_anos;
}

export default CartaoPerfilDoador;