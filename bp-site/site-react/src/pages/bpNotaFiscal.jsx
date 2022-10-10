import Menu from "../components/Menu";
import logo from '../img/logo.png';
import facebook from '../img/logo-facebook.png';
import linkedin from '../img/logo-linkedin.png';
import telegram from '../img/logo-telegram.png';
import picturePoint from '../img/estrela.png';
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";

function BpNotaFiscal(){
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
        float: left;
        margin-left: 30%;
        margin-top: 5%;
        color: #01396F;
        height: 65vh;
        background-color:${({ theme }) => theme.body};
        box-shadow: ${({ theme }) => theme.borda};
        color: ${({ theme }) => theme.azulClaro};
        top: 10%;
        position:absolute;
        
    `;
    const Divtext = styled.div`
        width: 100%;
        height: 10vh;
        margin-top:8%;
        float: left;
        font-size:25px;
        text-align: center;
    `;
    const imgPonto = {
        width: "50%",
        position: "relative",
        marginTop: "4vh",
    }
    const nomeOng = {
        fontSize: "50px",
        marginTop: "10px",
        color: "black",
    }
    const tituloNotaFiscal = {
        color: "black",
    }
    const valorNota = {
        color: "red",
        fontSize: "30px"
    }
    const tituloValor = {
        color: "black",
    }
    const textComp = {
        paddingTop:"15px",
        float: "left",
        color: "black",
        fontSize: "13px",
        marginLeft: "26%"
    }

    const caixa = {
        display: "block",
        marginTop: "90vh",
        height: "300px",
        width: "auto",
        boxShadow: "0px 0px 10px rgb(136, 136, 136)",
    }

    const alinha={
        float:"left",
        width:"33%"
    }

    const pontos = {
        textAlign:"center"
    }
    return <>

        <Menu funcaoDark={toggleTheme}/>
        <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
            <Fragment>
                <GlobalTheme />
                <DivLogin>
                    <Divtext>
                        <div><span style={nomeOng}>ActionAId</span></div>
                        <div><span style={tituloNotaFiscal}>Comprovante Fiscal </span></div>
                        <div><span style={tituloValor}>Valor doado:</span></div>
                        <div><span style={valorNota}>R$500,00</span></div>
                        <div>
                            <div style={alinha}><img style={imgPonto} src={picturePoint} /></div>
                            <div style={alinha}><br /><br /><span style={pontos}>200</span></div>
                            <div style={alinha}><img style={imgPonto} src={picturePoint} /></div>
                        </div>
                        <div><span style={textComp}>Nota emitida pela ONG ActionAid Ltda</span></div>
                    </Divtext>
                </DivLogin>
            </Fragment>
        </ThemeProvider>
        <footer>
            <div style={caixa}>
                <div className="div-logo">
                    <img src={logo} alt="logo" />
                    <h1>Better Place</h1>
                </div>
                <div className="div-info">
                    <p>E-mail: better.place@gmail.com<br />Tel: 4444-333</p>
                    <div className="img-redes-sociais">
                        <img src={facebook} alt="logo-facebook" />
                        <img src={linkedin} alt="logo-linkedin" />
                        <img src={telegram} alt="logo-telegram" />
                    </div>
                </div>

            </div>
        </footer>


    </>
}

export default BpNotaFiscal;