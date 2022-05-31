import { Link } from "react-router-dom";
import Input from "../components/Input";
import Menu from "../components/Menu";

function CadastroOng() {
    return (
        <>
            <Menu funcao="semCadastro"/>
            <div className="cadastro">
                <div class="cadastroOng">
                    <h1>Cadastre-se como ong:</h1>
                    <div class="esquerda">
                        <Input nome="Nome:" id="idNome" tipo="text"/>
                        <Input nome="Email:" id="idEmail" tipo="text"/>
                        <Input nome="Senha:" id="idSenha" tipo="password"/>
                        <Input nome="CNPJ:" id="idCnpj" tipo="text"/>
                        <Input nome="Rua:" id="idRua" tipo="text"/>
                        <Input nome="Bairro:" id="idBairro" tipo="text"/>
                    </div>
                    <div class="direita">
                        <Input nome="Usuário:" id="idUsuario" tipo="text"/>
                        <Input nome="Telefone:" id="idTelefone" tipo="text"/>
                        <Input nome="Confirmar senha:" id="idConfirmarSenha" tipo="password"/>
                        <Input nome="CEP:" id="idCep" tipo="text"/>
                        <Input nome="Numero:" id="idNumero" tipo="text"/>
                        <Input nome="Cidade:" id="idCidade" tipo="text"/>
                    </div>
                </div>
                <div class="botao">
                    <Link to="/login"><button class="btnCadastrar">Cadastrar</button><br/></Link>
                    <span>Para se cadastrar como Doador <a>clique aqui!</a></span>
                </div>
            </div>
            
            
        </>
    );
  }
  
  export default CadastroOng;



