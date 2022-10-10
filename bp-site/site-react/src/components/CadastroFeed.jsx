import Input from "../components/Input";
import styled from "styled-components";
import React, { useState } from "react";
import apiFeed from "../apiFeed";

function CadastroFeed(){

    async function enviar(e) {
        e.preventDefault();
        var descricao = document.getElementById("idDescricao").value;
        apiFeed.post("/feed/", {
            descricao: descricao,
            idOng: sessionStorage.getItem("idOng")
        }).then((resposta) => {
            console.log("post ok", resposta);
        })
    }
const DivCadastro = styled.div`
        float: left;
        width: 100%;
        height: 65.5vh;
        color: ${({ theme }) => theme.azulClaro};
        background-color: ${({ theme }) => theme.body};
        border-radius: 20px;
        border: ${({ theme }) => theme.bordaInfo} 2px solid;
    `;
    const Botao = styled.button`
        text-align: center;
        background-color: ${({ theme }) => theme.azulClaro};
        color: white;
        border: none;
        border-radius: 50px;
        box-shadow: ${({ theme }) => theme.borda};
        cursor:pointer;
        font-size: 24px;
        width: 40%;
        height: 5vh;
        margin-top: 6%;
        margin-left: 30%;
    `;
return (
    <DivCadastro>
        <Input tipo="text" id="idDescricao" nome="Descricao"/>
        <input type="file" id="idFoto"/>
        <Botao onClick={enviar}>Cadastrar</Botao>
    </DivCadastro>
);
}

export default CadastroFeed;