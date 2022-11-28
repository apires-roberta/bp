import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import apiCampanha from "../apiCampanha";
import ip from '../ip';
  
  function redirecionar(pagina) {
    window.location.href = `http://${ip}/`+pagina;
  }

function BotaoDoacao(props){
    const [funcData, setFuncData] = useState({
        idDoador: "",
        idCampanha: "",
        valorDoacao: ""
    })
    function doar(valor){
        var codigo=sessionStorage.getItem("idDoador")
        if(codigo==null){
          redirecionar("login")
        }
        else{
            funcData.idDoador = codigo;
            funcData.idCampanha = sessionStorage.getItem("campanha");
            funcData.valorDoacao = valor;
            apiCampanha.post("/doacao", {
                idDoador: funcData.idDoador,
                idCampanha: funcData.idCampanha,
                valorDoacao: funcData.valorDoacao
            }).then((resposta) => {
                console.log("post ok", resposta);
                redirecionar("NotaFiscal")
            })
        }
      }
    const Botao = styled.button`
        text-align: center;
        background-color: ${({ theme }) => theme.body};
        border: ${({ theme }) => theme.bordaBotao} 2px solid;
        border-radius: 50px;
        box-shadow: ${({ theme }) => theme.borda};
        cursor:pointer;
        font-size: 24px;
        width: 20%;
        height: 8vh;
        margin-top: 2%;
        margin-bottom: 2%;
        color: ${({ theme }) => theme.bordaInput};
        margin-left: 10%;
    `;
    return(
        <Botao onClick={()=>doar(props.valor)}>R${props.valor},00</Botao>
    );
}

export default BotaoDoacao;