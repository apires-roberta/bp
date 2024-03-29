import AlterarBranco from '../img/AlterarBranco.png'
import AlterarPreto from '../img/AlterarPreto.png'
import LockIcon from '@mui/icons-material/Lock';
import styled from "styled-components";
import apiCampanha from '../apiCampanha';
import LockOpenIcon from '@mui/icons-material/LockOpen';
import ip from '../ip';
function CardCampanhaOng(props) {
    function guardarCampanha(id,disponivel){
        if(!disponivel){
            apiCampanha.patch(`/campanha/indisponivel/ong/${sessionStorage.getItem("idOng")}`).then((resposta)=>
            {
                if(resposta.status===204){
                    apiCampanha.patch(`/campanha/disponibilidade/${id}`).then((resposta) => {
                        console.log("post ok", resposta);
                        redirecionar("campanha");
                    })
                }
            })
        }
        
      } 
    function alterarValorBanco(id){
        var valor = parseFloat(document.getElementById(`mudarValor${id}`).value.replace(",","."))
        apiCampanha.patch(`/campanha/alterarValor/campanha/${id}/valor/${valor}`).then((resposta) => {
          console.log("post ok", resposta);
          redirecionar("campanha");
      })
      }
      function Imagem(){
        const estiloLixeira = {
            float: "right",
            marginTop: "0"
        }
        if(props.validacao){
            return <LockOpenIcon sx={estiloLixeira} value="teste" id="teste"/>;
          }
          else{
            return <LockIcon sx={estiloLixeira}/>;
          }
      }
    const DivInfo = styled.div`
        width: 20%;
        margin-left: 6.8%;
        margin-top: 4%;
        padding-top: 3%;
        padding-left: 2%;
        padding-right: 2%;
        padding-bottom: 4%;
        float: left;
        box-shadow: ${({ theme }) => theme.borda};
        border-radius: 10px;
        color: ${({ theme }) => theme.logo};
        border: ${({ theme }) => theme.bordaInfo} 2px solid;
    `;

    const P = styled.p`
        margin-top: 20%;
        color: ${({ theme }) => theme.letraInfo};
        height: 15vh;
    `;

    const estiloH2 = {
        height: "4vh",
        float: "left",
        marginLeft: "15%",
        overflow: 'hidden'
    }

    const estiloDiv = {
        marginBottom: "5%"
    };

    const estiloImg = {
        float: "left",
        width: "10%",
        marginTop: "6%"
    }

    const estiloH3 = {
        float: "left",
        paddingTop: "7%",
        marginLeft: "3%",
        fontWeight: "normal"
    }

    const estiloH3Valor = {
        float: "left",
        paddingTop: "7%",
        fontWeight: "normal",
        display: "block"
    }

    const DivValor = styled.input`
        float: left;
        margin-left: 3%;
        width: 30%;
        margin-top: 7%;
        border: none;
        border-bottom:solid 2px ${({ theme }) => theme.azulClaro};
        background-color: transparent;
        color: ${({ theme }) => theme.logo};
        font-size: 16px;
        display: none;
        `;
    
    const estiloButton ={
        width: "30%",
        fontSize: "16px",
        marginTop: "7%",
        display: "none"
    }
    
    const localTheme = window.localStorage.getItem("theme");
    return (
        <>
            <DivInfo style={estiloDiv}>
                <div onClick={() => guardarCampanha(props.id, props.validacao)} style={{position:"absolute", float:"left"}}>
                    <Imagem/>
                </div>
                <h2 title={props.nome} style={estiloH2}>{props.nome}</h2>
                
                <P>{props.descCampanha}</P><br />
                <img onClick={()=>alterarValor(props.id)} style={estiloImg} className="img-estrela" src={localTheme === "light" ? AlterarPreto : AlterarBranco} alt="" />
                <h3 style={estiloH3}>R$</h3><DivValor id={`mudarValor${props.id}`}/> <button id={`botao${props.id}`} onClick={()=>alterarValorBanco(props.id)} style={estiloButton}>enviar</button>
                <h3 id={`valorFixo${props.id}`} style={estiloH3Valor}>{props.valorCampanha.toFixed(2).replace(".",",")}</h3>
            </DivInfo>
        </>
    );
}

export default CardCampanhaOng;

function redirecionar(pagina) {
    window.location.href = `http://${ip}/`+pagina;
  }

  function alterarValor(id){
    var input = document.getElementById(`mudarValor${id}`);
    var valor = document.getElementById(`valorFixo${id}`);
    var botao = document.getElementById(`botao${id}`);
    if(valor.style.display==="block"){    
        input.style = "display: inline;";
        botao.style = "display: inline; width: 30%; font-size: 16px; margin-top: 7%;";
        valor.style = "display: none;";
    }
    else{
        input.style = "display: none;";
        botao.style = "display: none;";
        valor.style = "display: block; float: left; padding-top: 7%; margin-left: 3%; font-weight: normal";
    }
  }

  