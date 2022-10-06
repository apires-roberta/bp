import styled from "styled-components";
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
    const logoPerfil={
        borderRadius: "50%",
        width: "90px"
    }
    const alinhamentoNome={
        paddingLeft: "2%",
        paddingTop: "3%"
    }
    const nome={
        fontSize: "20px"
    }
    const tempoPost={
        fontSize: "12px",
        color: "rgba(122, 122, 122, 0.9)"
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
    return(
        <>
        <Cartao>
        <div style={imgNome}>
                <div class="alinhamento-img">
                    <img style={logoPerfil} src="data:image/jpeg;base64,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" alt=""/>
                </div>
                <div style={alinhamentoNome}>
                    <span style={nome}>Actionaid</span><br/>
                    <span style={tempoPost}>Há 13 minutos</span>
                </div>
        </div>
        <div style={descricao}>
                Com as doações feitas, conseguimos arrecadar muitas coisas para ajudar pessoas vítimas de desastres naturais com cestas básicas agora momentos como esse de diversão ficam ainda melhores!!
        </div>
        <img style={imgFotoDoacao} src="https://www.bicworld.com/sites/default/files/2020-07/ActionAid_Project%20Photo_1-JULY-20.jpg" alt=""/>
        </Cartao>
        </>
    );
}

export default CartaoFeed;