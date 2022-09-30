import circulos from '../img/img-home-circulos-2.PNG';
import logo from '../img/logo.png';
import circulos2 from '../img/img-home-circulos.PNG';
import sorriso from '../img/emoji-sorriso.PNG';
import imagemHomem from '../img/imagem-homem-recebendo-doacao.PNG';
import logo2 from '../img/logo-2.png';
import facebook from '../img/logo-facebook.png';
import linkedin from '../img/logo-linkedin.png';
import telegram from '../img/logo-telegram.png';
import lua from "../img/Moon Symbol.png";
import sol from "../img/Vector.png";
import React, { Fragment, useState, useEffect } from "react";
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";


function BpHome(props) {

    const [theme, setTheme] = useState("light");

    const toggleTheme = () => {
        if (theme === "light") {
            window.localStorage.setItem("theme", "dark");
            setTheme("dark");
            props.funcaoDark("dark")
        } else {
            window.localStorage.setItem("theme", "light");
            setTheme("light");
            props.funcaoDark("light")
        }
    };

    useEffect(() => {
        const localTheme = window.localStorage.getItem("theme");
        localTheme && setTheme(localTheme);
    }, []);

    const estilo = {
        height:"30px",
        marginRigth:"10px"
    }

    const Contribuir = styled.div`
    background-color: ${({ theme }) => theme.body};
    color: ${({ theme }) => theme.logo};
    `;

    const Botao = styled.button`
    background-color: ${({ theme }) => theme.body};
    color: ${({ theme }) => theme.logo};
    cursor: pointer;
    `;

    const Box3 = styled.div`
    float: right;
    width: 100%;
    height: 130px;
    text-align: center;
    background-color: ${({ theme }) => theme.body};
    color: ${({ theme }) => theme.logo};
    `;

    const Box4 = styled.div`
    float: right;
    width: 100%;
    height: 130px;
    text-align: center;
    background-color: ${({ theme }) => theme.body};
    color: ${({ theme }) => theme.logo};
    `;

    const Sobre = styled.div`
    width: 50%;
    height: 200px;
    font-size: 20px;
    text-align: center;
    float: left;
    background-color: ${({ theme }) => theme.body};
    color: ${({ theme }) => theme.logo};
    `;
    
    const Diferenca = styled.div`
    font-weight: bold;
    font-size: 40px;
    padding: 30px;
    padding-top: 50px;
    background-color: ${({ theme }) => theme.body};
    color: ${({ theme }) => theme.logo};
    `;

    const Nomeh2 = styled.h2`
    font-size: 40px;
    color: ${({ theme }) => theme.logo};
    `;

    const TextoP = styled.p`
    font-size: 30px;
    margin-top: 5%;
    color: ${({ theme }) => theme.logo};
    `;

    const QuemSomos = styled.div`
    font-weight: bold;
    font-size: 40px;
    padding: 30px;
    padding-top: 50px;
    background-color: ${({ theme }) => theme.body};
    color: ${({ theme }) => theme.logo};
    `;

    const ValorDoacao = styled.div`
    float: left;
    width: 13.8%;
    height: 30%;
    margin: 3%;
    margin-top: 5%;
    background-color: ${({ theme }) => theme.body};
    color: ${({ theme }) => theme.logo};
    `;

    const Gallery = styled.div`
    font-size: 40px;
    padding-top: 40%;
    justify-items: center;
    justify-content: center;
    display: flex;
    color: ${({ theme }) => theme.logo};
    `;

    return (
        <>
        <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
        <Fragment>
             <GlobalTheme />
            <div class="imagem-circulos-2">
                <img src={circulos} alt="imagem-ilustrativa-circulos-2" />
            </div>
            
            <div class="menu">
                <div class="div-logo-menu">
                    <img src={logo} alt="logo" class="logo" />
                    <h1>bp</h1>
                </div>
                <div class="div-botoes-menu-lua">
                    <button><img style={estilo} alt="" onClick={toggleTheme} className="tema" src={theme === "light" ? lua : sol} /></button>
                </div>
                <div class="div-botoes-menu">
                    <Botao onClick={()=>redirecionar("campanha")}> Campanhas |</Botao>
                    <Botao class="botao-nav"> Sobre nós |</Botao>
                    <Botao onClick={()=>redirecionar("login")} class="botao-nav"> Entrar |</Botao>
                    <Botao onClick={()=>redirecionar("cadastro-doador")} class="botao-nav"> Cadastra-se |</Botao>
                 </div>
            </div>
            <div class="div-descricao-imagem-circulos">
                <div class="texto-descricao-bp">
                    <h1>Organizamos, <br />ouvimos e ajudamos.</h1>
                    <p>A bp tem como intuito ajudar ONGs e <br />
                        pessoas que querem ajudar a se conectarem. <br />
                        Dessa forma monstamos um sistema que <br />
                        cumpre com essa conexão.</p>
                </div>
                <div class="imagem-circulos">
                    <img src={circulos2} alt="imagem-ilustrativa-circulos" />
                </div>
            </div>

            <Contribuir class="box-2">
                <div class="div-por-que-contribuir">
                    <h1>
                        Por que <br />
                        contribuir?
                    </h1>
                    <img src={sorriso} alt="emoji-sorriso" />
                    <p>
                        Ao contribuir você ajuda <br />
                        uma comunidade inteira, <br />
                        pois o dinheiro <br />
                        normalmente é redistribuido <br />
                        em grandes lotes de <br />
                        recursos. Dessa forma você <br />
                        faz parte de algo maior, e <br /> com a sua ajuda todos nós <br />
                        saímos ganhando!
                    </p>
                </div>

                <div class="div-imagem-homem-recebendo-doacao">
                    <img src={imagemHomem} alt="imagem-homem-recebendo-doacao" />
                </div>
            </Contribuir>

            <Box3 class="box-3">
                <Diferenca>Faça a Diferença!</Diferenca>
            </Box3>

            <div class="box-4i">
                <ValorDoacao class="box">
                    <div class="gallery">
                        <Gallery> R$10,00 </Gallery>
                    </div>
                    <div class="desc">
                        <p>É possível contribuir com pouco</p>
                    </div>
                </ValorDoacao>

                <ValorDoacao class="box">
                    <div class="gallery">
                    <Gallery> R$20,00 </Gallery>
                    </div>
                    <div class="desc">
                        <p>Todos ao seu redor irão agradecer</p>
                    </div>
                </ValorDoacao>

                <ValorDoacao class="box">
                    <div class="gallery">
                    <Gallery> R$30,00 </Gallery>
                    </div>
                    <div class="desc">
                        <p>Uma pequena mudança faz a diferença</p>
                    </div>
                </ValorDoacao>

                <ValorDoacao class="box">
                    <div class="gallery">
                    <Gallery> R$40,00 </Gallery>
                    </div>
                    <div class="desc">
                        <p>Sua atitude pode influênciar aos outros</p>
                    </div>
                </ValorDoacao>

                <ValorDoacao class="box">
                    <div class="gallery">
                     <Gallery> R$50,00 </Gallery>
                    </div>
                    <div class="desc">
                        <p>Juntos somos mais fortes</p>
                    </div>
                </ValorDoacao>
            </div>

            <Box4 id="sobre" class="box-4">
                <QuemSomos>Quem somos?</QuemSomos>
            </Box4>

            <div class="box-5">
                <Sobre class="sobre">
                    <Nomeh2>bp</Nomeh2>
                    <TextoP>Somos uma empresa que <br /> buscar ajudar ONGs e <br /> pessoas que procuram doar.<br />
                        Com nossos sistema unimos <br /> as duas satisfazendo os dois <br />polos.
                    </TextoP>
                </Sobre>
                <div class="img-sobre">
                    <img src={logo2} alt="logo" />
                </div>
            </div>

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
            </Fragment>
            </ThemeProvider>
        </>
    );
}

export default BpHome;

function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/" + pagina;
}