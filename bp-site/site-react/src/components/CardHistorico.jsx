import AlterarBranco from '../img/AlterarBranco.png'
import AlterarPreto from '../img/AlterarPreto.png'
import NotaIcon from '../img/notaiconb.png'
import LockIcon from '@mui/icons-material/Lock';
import styled from "styled-components";
import apiCampanha from '../apiCampanha';
import LockOpenIcon from '@mui/icons-material/LockOpen';
import { width } from '@mui/system';

function HistoricoDoacao() {
const DivInfo = styled.div`
width: 50%;
margin-left: 22%;
margin-top: 5%;
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
float: "left",
width : "60%"
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
const notaicone ={
width : "13%",
height : "10vh",
marginTop: "0%",
float : "left",
marginLeft: "0%",
marginBottom : "0%"
}
const localTheme = window.localStorage.getItem("theme");
return (
<>
    <DivInfo style={estiloDiv}>
        <h2 >Valor : R$100,00</h2><br></br>
        <h2 >Para : Amigos do Bem</h2><br></br>
        <h2 >Campanha : Cadeira de Rodas</h2><br></br>
        <img style={notaicone} src={NotaIcon}/>
        <div>
        </div>
    </DivInfo>
    
</>
);
}

export default HistoricoDoacao;
