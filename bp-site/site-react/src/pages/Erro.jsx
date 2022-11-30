import logo from '../img/logo.png';
import facebook from '../img/logo-facebook.png';
import linkedin from '../img/logo-linkedin.png';
import telegram from '../img/logo-telegram.png';
import ip from '../ip';
function Erro(){
    function redirecionar(pagina) {
        window.location.href = `http://${ip}/` + pagina;
    }
    return (
        <>
            <div class="menu">
                <div class="div-logo-menu">
                    <img src={logo} alt="logo" class="logo" onClick={()=>redirecionar("/")}/>
                    <h1>bp</h1>
                </div>
                <div class="div-botoes-menu">
                    <button class="botao-nav" onClick={()=>redirecionar("campanha")}>Campanhas | </button>
                    <button class="botao-nav" onClick={()=>redirecionar("#sobre")}>Sobre nós | </button>
                    <button class="botao-nav" onClick={()=>redirecionar("login")}>Entrar | </button>
                    <button class="botao-nav" onClick={()=>redirecionar("cadastro-doador")}>Cadastra-se</button>
                </div>
            </div>
                <div class="texto-descricao-bp-erro">
                    <h1>Esta Página não está disponível</h1>
                    <p>O link pode não estar funcionando ou a Página pode ter sido removida. Verifique se o link que você está tentando abrir está correto.</p>
                </div>
            <footer>
                <div class="box-6" style={{marginTop:"23%"}}>
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

export default Erro;