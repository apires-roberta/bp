import AlterarBranco from '../img/AlterarBranco.png'
import AlterarPreto from '../img/AlterarPreto.png'
import LixeiraCinza from '../img/Lixeira.png'
import LixeiraVermelha from '../img/LixeiraHover.png'
import styled from "styled-components";
function CardCampanhaOng(props) {
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
        paddingTop: "8%",
        marginLeft: "3%",
        fontWeight: "normal"
    }
    const localTheme = window.localStorage.getItem("theme");
    return (
        <>
            <DivInfo style={estiloDiv}>
                <h2 style={estiloH2}>{props.nome}</h2>
                <img id='imgLixeira' onMouseOver={lixeiraMouse} onMouseLeave={lixeira} src={LixeiraCinza} style={estiloLixeira} alt="" /><br />
                <P>{props.descCampanha}</P><br />
                <img style={estiloImg} class="img-estrela" src={localTheme === "light" ? AlterarPreto : AlterarBranco} alt="" />
                <h3 style={estiloH3}>R${props.valorCampanha}</h3>
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