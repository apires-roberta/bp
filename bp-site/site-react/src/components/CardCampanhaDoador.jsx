import estrela from '../img/estrela.png';
import styled from "styled-components";
import apiLogin from "../apiLogin";
import { useEffect, useState } from 'react';

function CardCampanhaDoador(props){
    const [nome, setNome] = useState([]);
    useEffect(() => {
        apiLogin.get(`/bp/ong/${props.id}`).then((resposta) => {
          setNome(resposta.data.nome)
        })
      }, [])
    
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
    padding-top: 10%;
    color: ${({ theme }) => theme.letraInfo};
    height: 15vh;
`;

const estiloH2={
    height: "4vh",
    overflow: "hidden"
}

const estiloDiv = {
    marginTop: "6%",
    marginBottom: "0%"
};

const estiloImg = {
    float: "left",
    width: "20%"
}

const estiloH3 = {
    float: "left",
    paddingTop: "8%",
    marginLeft: "2%",
    fontWeight: "normal"
}
return (
    <>
        <DivInfo onClick={()=>redirecionar(props.campanha)} style={estiloDiv}>
            <h2 className='block-with-text' style={estiloH2} title={nome}>{nome}</h2>
            <P>{props.descCampanha}</P><br />
            <img style={estiloImg} className="img-estrela" src={estrela} alt="" />
            <h3 style={estiloH3}>R${props.valorCampanha}</h3>
        </DivInfo>
    </>
);
  }
  
  export default CardCampanhaDoador;

  function redirecionar(campanhaEscolhida) {
    sessionStorage.setItem("campanha", campanhaEscolhida)
    window.location.href = "http://localhost:3000/doacao";
  }