import Input from "../components/Input";
import styled from "styled-components";
import React from "react";
import apiFeed from "../apiFeed";

function CadastroFeed(){

    async function enviar(e) {
        e.preventDefault();
        /*var descricao = document.getElementById("idDescricao").value;
        apiFeed.post("/feed/", {
            descricao: descricao,
            idOng: sessionStorage.getItem("idOng")
        }).then((resposta) => {
            if(resposta.status===201){*/
            apiFeed.patch(`/feed/5`,{
                fotoFeed: document.getElementById('imagem').src
                
            //})}
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

    const estilo={
        display: "none"
    }
return (
    <DivCadastro>
        <Input tipo="text" id="idDescricao" nome="Descricao"/>
        <input onChange={imagem} type="file" id="idFoto"/>
        <img style={estilo} alt="" id="imagem"/>
        <Botao onClick={enviar}>Cadastrar</Botao>
    </DivCadastro>
);
}

export default CadastroFeed;
function imagem(){
    let file = document.getElementById('idFoto'); 
    let photo = document.getElementById('imagem'); 
    let reader = new FileReader();    
    reader.onload = () => {
        photo.src = reader.result;
    }
    reader.readAsDataURL(file.files[0]);
}