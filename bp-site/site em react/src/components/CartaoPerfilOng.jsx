import styled from "styled-components";
import { Link } from "react-router-dom";
function CartaoPerfilOng(){
    const Cartao = styled.div`
        height:85vh;
        margin-top:8%;
        width:70%;
        border-radius:14px;
        box-shadow: ${({ theme }) => theme.bordaFeed};
        background-color: ${({ theme }) => theme.body};
        color: ${({ theme }) => theme.logo};
        margin-left:15%;
        position:relative;
    `;
    const DivLogin = styled.div`
    width: 25%;
    float: left;
    margin-left: 10%;
    border-radius:10px;
    margin-top: 15%;
    background-color:${({ theme }) => theme.body};
    color: #01396F;
    height: 50vh;
    position:relative;
    z-index:98;
    box-shadow: ${({ theme }) => theme.bordaFeed};
    color: ${({ theme }) => theme.azulClaro};
    `;
    const Divtext = styled.div`
    height: 10vh;
    margin-top:10%;
    float: left;
    font-size:20px;
    text-align: center;
    width: 100%;
    `;
    const DivImagem = styled.div`
    position: absolute;
    width: 100%;
    `;
    const fotoFundo={
        height: "40vh",
        width: "100%",
        borderRadius: "10px 10px 0px 0px"
    }
    const fotoPerfil= {
        borderRadius: "3%",
        width: "100%"
    }
    const nomeOng={
        fontSize: "40px",
        color: "white"
    }
    const slogan={
        fontSize: "20px",
        color: "white",
    }
    const alinhaBtn={
        marginLeft: "51.5%",
        width: "50%",
        paddingTop:"26%",
    }
    const estiloBtn={
        marginLeft:"12%",
        marginTop: "5%"
    }
    return(
        <>
            <Cartao>
                <DivImagem>
                    <img style={fotoFundo} src="http://guiadefontes.msf.org.br/wp-content/uploads/2017/01/org-actionaid-1024x443.jpg   " alt=""/>
                </DivImagem>
                <span style={nomeOng}>Action Aid<br /></span>
                <span style={slogan}>Você pode construir um mundo melhor!</span>
                    <DivLogin>
                        <img alt="" style={fotoPerfil} src="https://captadores.org.br/wp-content/uploads/2021/04/actionaid-logo-vector.png" width="170" height="170"/>
                        <Divtext>
                            <span>Campanhas:<br></br></span><span>5</span><br /><br />
                            <span>Total arrecadado:</span> <span>R$1500,00</span>
                        </Divtext>
                    </DivLogin>
                    <div style={alinhaBtn}>
                        <Link to="/campanhas-ong"><button style={estiloBtn} class="btn">Campanha</button></Link>
                        <Link to="/feed"><button style={estiloBtn} class="btn">Feed</button></Link>
                        <Link to="/dashboard"><button style={estiloBtn} class="btn">Dashboard</button></Link>
                    </div>
            </Cartao>
        </>
    );
}

export default CartaoPerfilOng;