import facebook from '../img/logo-facebook.png';
import linkedin from '../img/logo-linkedin.png';
import telegram from '../img/logo-telegram.png';
import logo from '../img/logo.png';
import styled from "styled-components";
import React from "react";

function Rodape() {
    const DivRodape=styled.div`
    display: block;
    margin-top: 5%;
    height: 300px;
    width: auto;
    box-shadow: 0px 0px 10px rgb(136, 136, 136);
    `;
    return (
        <DivRodape>
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

        </DivRodape>
    );
}

export default Rodape;