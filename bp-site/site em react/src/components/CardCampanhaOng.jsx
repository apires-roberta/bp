import AlterarBranco from '../img/AlterarBranco.png'
import AlterarPreto from '../img/AlterarPreto.png'
import LixeiraCinza from '../img/Lixeira.png'
import LixeiraVermelha from '../img/LixeiraHover.png'
import styled from "styled-components";
import apiCampanha from '../apiCampanha';
function CardCampanhaOng(props) {
    function guardarCampanha(id){
        apiCampanha.delete(`/campanha/${id}`).then((resposta) => {
          console.log("post ok", resposta);
          redirecionar("campanhas-ong");
      })
      }
    function alterarValorBanco(id){
        var valor = parseFloat(document.getElementById("mudarValor").value)
        apiCampanha.patch(`/campanha/alterarValor/${id}/${valor}`).then((resposta) => {
          console.log("post ok", resposta);
          redirecionar("campanhas-ong");
      })
      }
    const DivInfo = styled.div`
        width: 20%;
        margin-left: 8%;
        margin-bottom: 5%;
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
        margin-top: 10%;
        color: ${({ theme }) => theme.letraInfo};
        height: 15vh;
    `;

    const estiloH2 = {
        height: "4vh",
        float: "left"
    }

    const estiloDiv = {
        marginBottom: "5%"
    };

    const estiloImg = {
        float: "left",
        width: "10%",
        marginTop: "6%"
    }

    const estiloLixeira = {
        float: "right",
        width: "8%"
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
        fontWeight: "normal"
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
        display: "none",
        backgroundColor: "red"
    }
    
    const localTheme = window.localStorage.getItem("theme");
    return (
        <>
            <DivInfo style={estiloDiv}>
                <h2 style={estiloH2}>{props.nome}</h2>
                <img onClick={()=>guardarCampanha(props.id)} id='imgLixeira' onMouseOver={lixeiraMouse} onMouseLeave={lixeira} src={LixeiraCinza} style={estiloLixeira} alt="" /><br />
                <P>{props.descCampanha}</P><br />
                <img onClick={alterarValor} style={estiloImg} class="img-estrela" src={localTheme === "light" ? AlterarPreto : AlterarBranco} alt="" />
                <h3 style={estiloH3}>R$</h3><DivValor id={"mudarValor"}/> <button id='botao' onClick={()=>alterarValorBanco(props.id)} style={estiloButton}>enviar</button>
                <h3 id={"valorFixo"} style={estiloH3Valor}>{props.valorCampanha}</h3>
            </DivInfo>
        </>
    );
}

export default CardCampanhaOng;

function lixeiraMouse() {
    var imgLixeira = document.getElementById("imgLixeira");
    imgLixeira.src = `${LixeiraVermelha}`;
}

function lixeira() {
    var imgLixeira = document.getElementById("imgLixeira");
    imgLixeira.src = `${LixeiraCinza}`;
}

function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/"+pagina;
  }

  function alterarValor(){
    var input = document.getElementById("mudarValor");
    var valor = document.getElementById("valorFixo");
    var botao = document.getElementById("botao");

    input.style = "display: inline;";
    botao.style = "display: inline; width: 30%; font-size: 16px; margin-top: 7%;";
    valor.style = "display: none;";
  }