import styled from "styled-components";
import { Link } from "react-router-dom";
import React, { useState, useEffect } from "react";
import apiCampanha from "../apiCampanha";
import apiLogin from "../apiLogin";
import contaBranco from "../img/usuarioBranco.png";
import contaPreto from "../img/usuarioPreto.png";
import capaBranco from "../img/fundoBranco.png";
function CartaoPerfilOng() {
    const [perfil, setperfil] = useState([]);
    const [qtdCampanha, setcampanha] = useState();
    const [qtdTotal, settotal] = useState();
    useEffect(() => {
        apiLogin.get(`/bp/ong/${sessionStorage.getItem("idOng")}`).then((resposta) => {
            if (resposta.status === 200) {
                setperfil([resposta.data])
                console.log(resposta.data)
            }
        })
        apiCampanha.get(`/doacao/total/ong/${sessionStorage.getItem("idOng")}`).then((resposta) => {
            if (resposta.status === 200) {
                console.log(resposta.data)
                settotal(resposta.data.toFixed(2).replace(".", ","))
            }
        })
        apiCampanha.get(`/campanha/quantidade/ong/${sessionStorage.getItem("idOng")}`).then((resposta) => {
            if (resposta.status === 200) {
                console.log(resposta.data)
                setcampanha(resposta.data)
            }
        })
    }, [])
    const localTheme = window.localStorage.getItem("theme");
    const Cartao = styled.div`
        height:80vh;
        margin-top:8%;
        width:70%;
        border-radius:14px;
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
        height: 50vh;
        position:relative;
        z-index:98;
        box-shadow: ${({ theme }) => theme.bordaFeed};
        color: ${({ theme }) => theme.azulClaro};
    `;
    const Divtext = styled.div`
        height: 10vh;
        margin-top:10%;
        float: left;
        font-size:20px;
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
        fontSize: "40px",
        color: "white"
    }
    const slogan = {
        fontSize: "20px",
        color: "white",
    }
    const alinhaBtn = {
        marginLeft: "51.5%",
        width: "50%",
        paddingTop: "32%",
    }
    const estiloBtn = {
        marginLeft: "12%",
        marginTop: "5%"
    }
    const estilo = {
        float: "right",
        zIndex: "3",
        position: "relative",
        width: "50%",
        marginTop: "20%",
        marginRight: "5%"
    }
    return (
        <>
            <Cartao>{ 
                perfil.map((item) => (
                    <>
                        <DivImagem>
                            <img style={fotoFundo} src={item.fotoCapa == null ? capaBranco : item.fotoCapa} alt="" />
                        </DivImagem>
                        <div style={estilo}>
                            <span style={nomeOng}>{item.nome}<br /></span>
                            <span style={slogan}>{item.bio}</span>
                        </div>
                        <DivLogin>
                            <img alt="" style={fotoPerfil} src={item.fotoPerfil == null ? localTheme === "light" ? contaPreto : contaBranco : item.fotoPerfil} width="170" height="170" />
                            <Divtext>
                                <span>Campanhas:<br></br></span><span>{qtdCampanha}</span><br /><br />
                                <span>Total arrecadado:</span> <span>R${qtdTotal}</span>
                            </Divtext>
                        </DivLogin>
                        <div style={alinhaBtn}>
                            <Link to="/campanha"><button style={estiloBtn} className="btn">Campanha</button></Link>
                            <Link to="/feed"><button style={estiloBtn} className="btn">Feed</button></Link>
                            <Link to="/dashboard"><button style={estiloBtn} className="btn">Dashboard</button></Link>
                        </div>
                    </>
                ))
            }
            </Cartao>
        </>
    );
}

export default CartaoPerfilOng;