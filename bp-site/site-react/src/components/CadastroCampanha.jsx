import Input from "../components/Input";
import styled from "styled-components";
import React, { useState } from "react";
import apiCampanha from "../apiCampanha";

function CadastroCampanha() {
    const funcData = useState({
        nomeCampanha: "",
        nomeItem: "",
        descCampanha: "",
        valorNecessario: "",
        tipoCampanha:""

    })

    async function enviar(e) {
            e.preventDefault();
            funcData.nomeCampanha = document.getElementById("idNomeCampanha").value;
            funcData.nomeItem = document.getElementById("idNomeItem").value;
            funcData.descCampanha = document.getElementById("idDesc").value;
            funcData.tipoCampanha = document.getElementById("tipoCampanha").value;
            funcData.valorNecessario = parseFloat(document.getElementById("idValor").value);
            console.log(funcData);
            apiCampanha.post("/campanha/", {
                nomeCampanha: funcData.nomeCampanha,
                nomeItem: funcData.nomeItem,
                descCampanha: funcData.descCampanha,
                valorNecessario: funcData.valorNecessario,
                fkOng: sessionStorage.getItem("idOng"),
                tipoCampanha: funcData.tipoCampanha
            }).then((resposta) => {
                console.log("post ok", resposta);
                sessionStorage.setItem("idCampanha", resposta.data)
                redirecionar("campanha")
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

    const Select = styled.select`
        text-align: center;
        width: 62%;
        margin-left: 20%;
        border-radius: 30px;
        height: 6vh;
        background-color: transparent;
        border: ${({ theme }) => theme.bordaInput} 2px solid;
        color: ${({ theme }) => theme.letraInput};
    `;

    const Option = styled.option`
        background-color: ${({ theme }) => theme.menu};
    `;

    const Span = styled.span`
        margin-left: 23%;
    `;

    return (
        <>
            <DivCadastro>
                <Input nome="Nome campanha:" id="idNomeCampanha" tipo="text" />
                <Input nome="Nome item:" id="idNomeItem" tipo="text" />
                <Input nome="Descrição:" id="idDesc" tipo="text" />
                <Input nome="Valor:" id="idValor" tipo="text" />
                <div className="inputDiv">
                <Span>Tipo campanha:</Span><br/>
                <Select id="tipoCampanha">
                    <Option value=""></Option>
                    <Option value="FOME">Fome</Option>
                    <Option value="SAUDE">Saude</Option>
                    <Option value="ROUPA">Roupa</Option>
                    <Option value="OUTROS">Outros</Option>
                </Select>
                </div>
                <Botao onClick={enviar}>Cadastrar</Botao>
            </DivCadastro>
        </>
    );
}

export default CadastroCampanha;

function redirecionar(pagina) {
    window.location.href = "http://localhost:3000/"+pagina;
  }