import Menu from "../components/Menu";
import logo from '../img/logo.png';
import facebook from '../img/logo-facebook.png';
import linkedin from '../img/logo-linkedin.png';
import telegram from '../img/logo-telegram.png';
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
        top: 8%;
        
    `;
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

<Menu funcao="menunota">
</Menu>
<ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
                <Fragment>
                    <GlobalTheme />
<DivLogin>
</DivLogin>
</Fragment>
</ThemeProvider>
<footer>
                <div class="box-6">
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
