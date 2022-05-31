import { Link } from "react-router-dom";
import Input from "../components/Input";
import Menu from "../components/Menu";


function CadastroDoador() {
    return (
        <>
            <Menu funcao="semCadastro"/>
            <div className="cadastro">
                <div class="cadastroDoador">
                    <h1>Cadastre-se como doador:</h1>
                    <div class="esquerda">
                        <Input nome="Nome:" id="idNome" tipo="text"/>
                        <Input nome="Email:" id="idEmail" tipo="text"/>
                        <Input nome="Senha:" id="idSenha" tipo="password"/>
                        <Input nome="CPF:" id="idCpf" tipo="text"/>
                    </div>
                    <div class="direita">
                        <Input nome="Usuário:" id="idUsuario" tipo="text"/>
                        <Input nome="Telefone:" id="idTelefone" tipo="text"/>
                        <Input nome="Confirmar senha:" id="idConfirmarSenha" tipo="password"/>
                        <Input nome="Data Nascimento:" id="idData" tipo="date"/>
                    </div>
                </div>
                <div class="botao">
                    <Link to="/login"><button class="btnCadastrar">Cadastrar</button><br/></Link>
                    <span>Para se cadastrar como Ong <a>clique aqui!</a></span>
                </div>
            </div>
            
        </>
    );
  }
  
  export default CadastroDoador;
