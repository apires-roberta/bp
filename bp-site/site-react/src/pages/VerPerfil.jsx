import styled from "styled-components";
import Menu from "../components/Menu";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import React, { Fragment, useState, useEffect } from "react";
import Rodape from "../components/Rodape";
import CartaoFeedOng from "../components/CartaoFeedOng";
import "../css/styles.css";
import apiFeed from "../apiFeed";
import ipBack from "../ipBack";

function VerPerfil() {
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
    width: 15%;
    float: left;
    margin-left: 25%;
    border-radius:10px;
    margin-top: 12.5%;
    background-color:${({ theme }) => theme.body};
    position:relative;
    z-index:98;
    box-shadow: ${({ theme }) => theme.bordaFeed};
    color: ${({ theme }) => theme.azulClaro};
    `;
    const DivAlinhaSeguir = styled.div`
    width: 20%;
    height: 35px;
    float: left;
    margin-left: 18%;
    border-radius:10px;
    margin-top: 25%;
    position:relative;
    z-index:98;
    box-shadow: ${({ theme }) => theme.bordaFeed};
    color: ${({ theme }) => theme.azulClaro};
    background-color:${({ theme }) => theme.body};
    `;
    const DivImagem = styled.div`
    position: absolute;
    width: 60%;
    margin-left: 20%;
    margin-top: 8%;
    `;
    const fotoFundo = {
        height: "40vh",
        width: "100%",
        borderRadius: "10px 10px 0px 0px",
        float: "left"
    }
    const [bgColour, setBgColour] = useState("")
    const BtnSeguir = styled.button`
        width: 100%;
        height: 35px;
        color: ${({ theme }) => theme.logo};
        box-shadow: ${({ theme }) => theme.bordaFeed};
        background-color:${bgColour};
        border-radius:10px;
    `;

    const [info, setInfo] = useState([]);
    const [verifica, setVerifica] = useState();

    useEffect(() => {
        fetch(
            `http://${ipBack}:8080/bp/ong/${sessionStorage.getItem('cod')}`
        )
            .then((response) => response.json())
            .then((response) => {
                setInfo(response);
                console.log(response);
                console.log(info);

            })
    }, []);

    useEffect(() => {
        fetch(
            `http://${ipBack}/inscricao/existe/doador/${sessionStorage.getItem('cod')}/ong/${sessionStorage.getItem('idDoador')}`
        ).then(function(response) {
            console.log(response.ok);
            setVerifica(response.ok);
            
        })
    }, []);



    const seguir = () => {
        apiFeed.post("inscricao", {
            fkOng: sessionStorage.getItem('cod'),
            fkDoador: sessionStorage.getItem('idDoador')
        })
        setVerifica(true);
    }

    const Naoseguir = () => {
        apiFeed.delete(`inscricao/doador/${sessionStorage.getItem('idDoador')}/ong/${sessionStorage.getItem('cod')}`, {
            
        })
        setVerifica(false);
    }

    return (
        <>
            <Menu funcaoDark={toggleTheme} funcao="cadastro" />
            <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>

                <Fragment>
                    <GlobalTheme />
                    <DivImagem>
                        <img style={fotoFundo} src={`${info.fotoCapa}`} alt="" />
                    </DivImagem>
                    <DivLogin>
                        <img className="logoPerfilView" src={`${info.fotoPerfil}`} alt="" />
                    </DivLogin>
                    <DivAlinhaSeguir>
                        {verifica ? (
                            <BtnSeguir onClick={Naoseguir} onMouseEnter={() => setBgColour("#0070DC")} onMouseLeave={() => setBgColour("")}> Deixar de Seguir </BtnSeguir>
                        ) : (
                            <BtnSeguir onClick={seguir} onMouseEnter={() => setBgColour("#0070DC")} onMouseLeave={() => setBgColour("")}> Seguir </BtnSeguir>
                        )}

                    </DivAlinhaSeguir>

                    <br /><br /><br /><br /><br /><br /><br /><br />
                    <br /><br /><br /><br /><br /><br /><br /><br />
                    <br />
                    <CartaoFeedOng />
                    <Rodape/>
                </Fragment>

            </ThemeProvider>
        </>
    );
}

export default VerPerfil;
