import Input from "../components/Input";
import styled from "styled-components";
import React from "react";

function CadastroCampanha() {

    const DivCadastro = styled.div`
        float: left;
        width: 40%;
        margin-left: 26%;
        margin-top: 3%;
        height: 60vh;
        box-shadow: ${({ theme }) => theme.borda};
        color: ${({ theme }) => theme.azulClaro};
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
        <>
                    <DivCadastro>
                        <Input nome="Nome campanha:" id="idNomeCampanha" tipo="text" />
                        <Input nome="Nome item:" id="idNomeItem" tipo="text" />
                        <Input nome="Descrição:" id="idDesc" tipo="text" />
                        <Input nome="Valor:" id="idValor" tipo="text" />
                        <Botao>Cadastrar</Botao>
                        
                    </DivCadastro>
        </>
    );
}

export default CadastroCampanha;
