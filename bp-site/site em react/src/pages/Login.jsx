import { Link } from "react-router-dom";
import Input from "../components/Input";
import Menu from "../components/Menu";

function Login() {
    return (
        <>
            <Menu funcao="semCadastro"/>
            <div class="login">
                <h1>Login</h1>
                <Input nome="Email:" id="idEmail" tipo="text"/>
                <Input nome="Senha:" id="idSenha" tipo="password"/>
                <Link to="/doacao"><button class="btnLogar">Entrar</button><br/></Link>
                <a>Esqueceu a senha?</a>
            </div>
            <div class="divCadastrar">
                <span>Cadastre-se como:</span><br/>
                <button>Ong</button>
                <button class="btnDireita">Doador</button>
            </div>
        </>
    );
  }
  
  export default Login;