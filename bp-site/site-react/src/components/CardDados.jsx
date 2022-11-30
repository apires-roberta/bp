import estrela from '../img/estrela.png'
import styled from "styled-components";
import apiCampanha from "../apiCampanha";
import React, { useState, useEffect } from "react";

function CardDados(props) {
    const [campanha, setcampanha] = useState([]);
    const [valor, setValor] = useState([]);
    useEffect(() => {
        apiCampanha.get(`/campanha/${props.id}`).then((resposta) => {
          if (resposta.status === 200) {  
            console.log(resposta.data)
            setcampanha(resposta.data)
            setValor(resposta.data.valorNecessario.toFixed(2).replace(".",","))
          }
        })
      }, [])
    const DivInfo = styled.div`
      width: 19%;
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

    const P=styled.p`
        padding-top: 20%;
        color: ${({ theme }) => theme.letraInfo};
        height: 15vh;
    `;

    const H2 = styled.h2`
    color: ${({ theme }) => theme.azulClaro};
`;

    const estiloH2={
        float: "left",
        height: "4vh",
        overflow: "hidden"
    }
    
    const estiloImg={
        float: "left",
        width: "20%"
    }
    
    const estiloH3={
        float: "left",
        paddingTop: "8%",
        marginLeft: "2%",
        fontWeight: "normal"
    }

    const estiloH2Mes = {
        float: "right",
        fontWeight: "normal"
    }
    return (
        <>
            <DivInfo style={props.style}>
                <h2 style={estiloH2}>{campanha.nomeCampanha}</h2>
                <H2 style={estiloH2Mes}>{props.mes}</H2>
                <P>{campanha.descCampanha}</P><br />
                <img style={estiloImg} className="img-estrela" src={estrela} alt="" />
                <h3 style={estiloH3}>R${valor}</h3>
            </DivInfo>
        </>
    );
}

export default CardDados;