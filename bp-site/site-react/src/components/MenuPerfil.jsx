import styled from "styled-components";
import apiLogin from "../apiLogin";
import ip from '../ip';
function MenuPerfil(){
    async function logout(){
        if(sessionStorage.getItem('tipo')==="PerfilOng"){
            apiLogin.delete(`/bp/ong/logoff/${sessionStorage.getItem('idOng')}`).then((resposta) => {
                console.log("post ok", resposta);
                sessionStorage.setItem("tipo", "");
                sessionStorage.setItem("idOng", '')
                redirecionar("login");
            })
        }
        else{
            apiLogin.delete(`/bp/doador/logoff/${sessionStorage.getItem('idDoador')}`).then((resposta) => {
                console.log("post ok", resposta);
                sessionStorage.setItem("idDoador", "")
                sessionStorage.setItem("tipo", "");
                redirecionar("login");
            })
        }
    }
    const P = styled.p`
        padding: 1vh;
        border: solid 1px #CCCCCC;
        cursor:pointer;
        color: ${({ theme }) => theme.logo};
    `;

    const Div = styled.div`
        background-color: ${({ theme }) => theme.body};
        width: 20%;
        z-index:100;
        float: left;
        margin-left: 75%;
        text-align: center;
        display: none;
        box-shadow: 10px 10px 50px 5px rgba(0, 0, 0, 0.2);
    `;
    if(sessionStorage.getItem("tipo")==='PerfilOng'){
        return (
            <>
               <Div onMouseEnter={manterMenu} onMouseLeave={fecharMenu} id={"divMenu"}>
                   <P onClick={()=>redirecionar("perfil")}>Minha conta</P>
                   <P onClick={()=>redirecionar("feed")}>Feed</P>
                   <P onClick={()=>redirecionar("AlterarDados")}>Alterar dados</P>
                   <P onClick={logout}>Logout</P>
               </Div>
            </>
        );
    }
    else{
        return (
            <>
               <Div onMouseEnter={manterMenu} onMouseLeave={fecharMenu} id={"divMenu"}>
                   <P onClick={()=>redirecionar("perfil")}>Minha conta</P>
                   <P onClick={()=>redirecionar("feed")}>Feed</P>
                   <P onClick={()=>redirecionar("AlterarDados")}>Alterar dados</P>
                   <P onClick={()=>redirecionar("Historico")}>Historico doacao</P>
                   <P onClick={logout}>Logout</P>
               </Div>
            </>
        );
    }
    
}



export default MenuPerfil;

function manterMenu(){
    document.getElementById("divMenu").style.display="block";
}

function fecharMenu(){
    document.getElementById("divMenu").style.display="none";
}

function redirecionar(pagina) {
    window.location.href = `http://${ip}/`+pagina;
  }