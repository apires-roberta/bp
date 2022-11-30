import '../css/styles.css';
import styled from "styled-components";
import ipBack from "../ipBack";
import React, { useEffect, useState } from 'react';
function CartaoFeed(){
    const Cartao = styled.div`
        heigth: 200px;
        margin-top: 8%;
        margin-left: 20%;
        box-shadow: ${({ theme }) => theme.bordaFeed};
        width: 60%;
        background-color: ${({ theme }) => theme.body};
        color: ${({ theme }) => theme.logo};
    `;
    const imgNome={
        padding:"2%",
        paddingLeft:"6%",
        display:"flex"
    }
    const alinhamentoNome={
        paddingLeft: "2%",
        paddingTop: "3%"
    }
    const SpanEstilo = styled.span`
    @import url(https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap);
    color: ${({ theme }) => theme.logo};
    width: 280px;
    font-size: 25px;
    font-family: 'Comfortaa', cursive;
   `;
    const tempoPost={
        fontSize: "15px",
        color: "rgba(122, 122, 122, 0.9)",
    }
    const descricao={
        paddingTop: "1%",
        paddingLeft: "7%",
        paddingRight: "7%",
        paddingBottom: "3%",
        fontSize: "18px",
        text: "justify"
    }
    const imgFotoDoacao={
        width: "100%",
        marginTop:"2%",
        marginBottom:"2%",
        height: "55vh"
    }

    const [info, setInfo] = useState([]);

    useEffect(() => {
        fetch(
            `http://${ipBack}:8081/feed`
          )
            .then((response) => response.json())
            .then((response) => {
              setInfo(response);
              console.log(response);
            })
    }, []);

    function FormataStringData(data) {
        let data_americana = data.substr(0, 10);
        let data_brasileira = data_americana.split('-').reverse().join('/');
      
        return data_brasileira;
    }

    return(
        <>
        { info.map(item => (
        <Cartao key={item.codigo} >
            <div style={imgNome} >
                <div className="alinhamento-img" >
                  <img className="logoPerfil" src={item.ong.fotoPerfil} alt="" />
                </div>
                <div style={alinhamentoNome}>
                    <SpanEstilo>{item.ong.nome}</SpanEstilo><br/>
                    <span style={tempoPost}>{FormataStringData(item.dataPublicacao)}</span>
                </div>
            </div>
            <div style={descricao}>{item.descricao}</div>
            <img className="imgFotoDoacao" src={item.fotoFeed} alt="" />
        
        </Cartao>
        ))}
        </>
    );
}

export default CartaoFeed;