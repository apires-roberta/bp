import styled from "styled-components";

function BotaoDoacao(props){
    const Botao = styled.button`
        text-align: center;
        background-color: ${({ theme }) => theme.body};
        border: ${({ theme }) => theme.bordaBotao} 2px solid;
        border-radius: 50px;
        box-shadow: ${({ theme }) => theme.borda};
        cursor:pointer;
        font-size: 24px;
        width: 25%;
        height: 8vh;
        margin-top: 2%;
        margin-bottom: 2%;
        color: ${({ theme }) => theme.bordaInput};
        margin-left: 5%;
    `;
    return(
        <Botao>R${props.valor},00</Botao>
    );
}

export default BotaoDoacao;