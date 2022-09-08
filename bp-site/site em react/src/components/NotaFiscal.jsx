import Menu from "../components/Menu";
import logo from '../img/logo.png';
import facebook from '../img/logo-facebook.png';
import linkedin from '../img/logo-linkedin.png';
import telegram from '../img/logo-telegram.png';
import picturePoint from '../img/picwish.png';
import styled from "styled-components";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import React, { Fragment, useState, useEffect } from "react";


function NotaFiscal(){
    const DivLogin = styled.div`
        width: 40%;
        float: left;
        margin-left: 30%;
        margin-top: 5%;
        color: #01396F;
        height: 60vh;
        box-shadow: ${({ theme }) => theme.borda};
        color: ${({ theme }) => theme.azulClaro};
        top: 10%;
        position:absolute;
        
    `;
    const Divtext = styled.div`
    width: 80%;
    height: 10vh;
    margin-top:10%;
        float: left;
        margin-left: 15%;
        font-size:25px;
    `;
    const imgPonto={
        width:"30%",
        position: "relative",
        marginTop:"-4vh",
        marginLeft:"28%",
    }
    const footerNota ={
        marginTop:"900px",
    } 
    const nomeOng ={
        fontSize:"50px",
        marginTop:"10px",
        color:"black",
        marginLeft:"22%"
    } 
    const tituloNotaFiscal ={
        color:"black",
        marginLeft:"21%"
        
    } 
    const valorNota ={
        color:"red",
        marginLeft:"33%",
        fontSize:"30px"
    } 
    const tituloValor ={
        color:"black",
        marginLeft:"29%"
    } 
    const descNota ={
        color:"black",
        marginTop:"10px",
        
    } 
    const textComp ={
        marginTop:"30px",
        float:"left",
        color:"black",
        fontSize:"13px",
        marginLeft:"19%"
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
return<>

<Menu funcaoDark="toogleTheme">
</Menu>
<ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
<DivLogin>
    <Divtext>
    <span style={nomeOng}>ActionAId</span><br></br><br></br>
    <span style={tituloNotaFiscal}>Comprovante Fiscal </span><br></br><br></br>
    <span style={tituloValor}>Valor doado:</span><br></br>
    <span style={valorNota}>R$500,00</span><br></br><br></br><br></br>
    <img style={imgPonto} src={picturePoint}/>
    <span style={textComp}>Nota emitida pela ONG ActionAid Ltda</span>
    </Divtext>
</DivLogin>
</Fragment>
</ThemeProvider>
<footer>
                <div class="box99">
                    <div class="div-logo">
                        <img src={logo} alt="logo" />
                        <h1>Better Place</h1>
                    </div>
                    <div class="div-info">
                        <p>E-mail: better.place@gmail.com<br />Tel: 4444-333</p>
                        <div class="img-redes-sociais">
                            <img src={facebook} alt="logo-facebook" />
                            <img src={linkedin} alt="logo-linkedin" />
                            <img src={telegram} alt="logo-telegram" />
                        </div>
                    </div>

                </div>
            </footer>


</>
}

export default NotaFiscal;
