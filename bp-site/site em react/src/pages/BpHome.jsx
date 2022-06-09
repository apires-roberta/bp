import circulos from '../img/img-home-circulos-2.PNG';
import logo from '../img/logo.png';
import circulos2 from '../img/img-home-circulos.PNG';
import sorriso from '../img/emoji-sorriso.PNG';
import imagemHomem from '../img/imagem-homem-recebendo-doacao.PNG';
import logo2 from '../img/logo-2.png';
import facebook from '../img/logo-facebook.png';
import linkedin from '../img/logo-linkedin.png';
import telegram from '../img/logo-telegram.png';

function BpHome() {

    return (
        <>
            <div class="imagem-circulos-2">
                <img src={circulos} alt="imagem-ilustrativa-circulos-2" />
            </div>

            <div class="menu">
                <div class="div-logo-menu">
                    <img src={logo} alt="logo" class="logo" />
                    <h1>bp</h1>
                </div>
                <div class="div-botoes-menu">
                    <button onClick={()=>redirecionar("campanhas-doador")} class="botao-nav">Campanhas | </button>
                    <button class="botao-nav">Sobre nós | </button>
                    <button onClick={()=>redirecionar("login")} class="botao-nav">Entrar | </button>
                    <button onClick={()=>redirecionar("cadastro-doador")} class="botao-nav">Cadastra-se</button>
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

            <div class="box-2">
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
            </div>

            <div class="box-3">
                <h2>Faça a Diferença!</h2>
            </div>

            <div class="box-4i">
                <div class="box">
                    <div class="gallery">
                        <h2> R$10,00 </h2>
                    </div>
                    <div class="desc">
                        <p>É possível contribuir com pouco</p>
                    </div>
                </div>

                <div class="box">
                    <div class="gallery">
                        <h2> R$20,00 </h2>
                    </div>
                    <div class="desc">
                        <p>Todos ao seu redor irão agradecer</p>
                    </div>
                </div>

                <div class="box">
                    <div class="gallery">
                        <h2> R$30,00 </h2>
                    </div>
                    <div class="desc">
                        <p>Uma pequena mudança faz a diferença</p>
                    </div>
                </div>

                <div class="box">
                    <div class="gallery">
                        <h2> R$40,00 </h2>
                    </div>
                    <div class="desc">
                        <p>Sua atitude pode influênciar aos outros</p>
                    </div>
                </div>

                <div class="box">
                    <div class="gallery">
                        <h2> R$50,00 </h2>
                    </div>
                    <div class="desc">
                        <p>Juntos somos mais fortes</p>
                    </div>
                </div>
            </div>

            <div id="sobre" class="box-4">
                <h2>Quem somos?</h2>
            </div>

            <div class="box-5">
                <div class="sobre">
                    <h2>bp</h2>
                    <p>Somos uma empresa que <br /> buscar ajudar ONGs e <br /> pessoas que procuram doar.<br />
                        Com nossos sistema unimos <br /> as duas satisfazendo os dois <br />polos.
                    </p>
                </div>
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
        </>
    );
}

export default BpHome;

function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/" + pagina;
}