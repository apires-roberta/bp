import '../css/styles.css';
import styled from "styled-components";
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
        fontSize: "18px"
    }
    const imgFotoDoacao={
        width: "100%",
        marginTop:"2%",
        marginBottom:"2%",
        height: "55vh"
    }

    const [info, setInfo] = useState([]);

    useEffect(() => {
        //getInstaFeed()
        fetch(
            `http://localhost:8081/feed`
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
        // Utilizo o .slice(-2) para garantir o formato com 2 digitos.
    }

    return(
        <>
        { info.map(item => (
        <Cartao key={item.codigo} >
            <div style={imgNome} >
                <div className="alinhamento-img" >
                  <div className="logoPerfil" style={{ backgroundImage: `url(data:image/jpeg;base64,${item.fotoPerfilOng}`, float: 'left' }}></div>
                </div>
                <div style={alinhamentoNome}>
                    <SpanEstilo>{item.ong.nome}</SpanEstilo><br/>
                    <span style={tempoPost}>{FormataStringData(item.dataPublicacao)}</span>
                </div>
            </div>
            <div style={descricao}>{item.descricao}</div>
            <div className="imgFotoDoacao" style={{ backgroundImage: `url(data:image/jpeg;base64,${item.fotoFeed}`}}></div>
        
        </Cartao>
        ))}
        </>
    );
}

export default CartaoFeed;