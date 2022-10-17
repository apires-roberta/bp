import facebook from '../img/logo-facebook.png';
import linkedin from '../img/logo-linkedin.png';
import telegram from '../img/logo-telegram.png';
import facebookBranco from '../img/logo-facebook_branco.png';
import linkedinBranco from '../img/logo-linkedin_branco.png';
import telegramBranco from '../img/logo-telegram_branco.png';
import logo from '../img/logo.png';
import styled from "styled-components";
import React from "react";

function Rodape() {
    const localTheme = window.localStorage.getItem("theme");
    const DivRodape=styled.footer`
    display: block;
    margin-top: 5%;
    height: 300px;
    width: auto;
    box-shadow: 0px 0px 10px rgb(136, 136, 136);
    color: ${localTheme === "light" ? "#6C6C6C":"rgb(204, 204, 204)"}
    `;
    return (
        <DivRodape>
            <div className="div-logo">
                <img src={logo} alt="logo" />
                <h1>Better Place</h1>
            </div>
            <div className="div-info">
                <p>E-mail: better.place@gmail.com<br />Tel: 4444-333</p>
                <div className="img-redes-sociais">
                    <img src={localTheme === "light" ? facebook:facebookBranco} alt="logo-facebook" />
                    <img src={localTheme === "light" ? linkedin:linkedinBranco} alt="logo-linkedin" />
                    <img src={localTheme === "light" ? telegram:telegramBranco} alt="logo-telegram" />
                </div>
            </div>

        </DivRodape>
    );
}

export default Rodape;