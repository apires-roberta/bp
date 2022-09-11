import { blue } from "@mui/material/colors";
import styled from "styled-components";
import { Link, useNavigate } from "react-router-dom";
function CartaoPerfilOng(){
    const Cartao = styled.div`
        height:80vh;
        margin-top:8%;
        width:70%;
        border-radius:14px;
        box-shadow: ${({ theme }) => theme.bordaFeed};
        background-color: ${({ theme }) => theme.body};
        color: ${({ theme }) => theme.logo};
        margin-left:15%;
    `;
    const DivLogin = styled.div`
        width: 100%;
        float: left;
        margin-left: 5%;
        border-radius:10px;
        background-color: ${({ theme }) => theme.body};
        color: #01396F;
        height: 55vh;
        position:relative;
        z-index:99;
        box-shadow: ${({ theme }) => theme.bordaFeed};
        color: ${({ theme }) => theme.azulClaro};
    `;
    const Divtext = styled.div`
        width: 100%;
        height: 10vh;
        margin-top:13%;
        float: left;
        font-size:25px;
        text-align: center;
    `;
    const DivImagem = styled.div`
        z-index:0;
    `;
    const fotoFundo={
        height: "40vh",
        width: "100%",
        borderRadius:"10px 10px 0px 0px"
    }
    const fundoFotoPerfil={
        height: "30vh",
        width: "40vh",
        marginTop: "-12vh",
        marginLeft: "8vh",
    }
    const fotoPerfil= {
        marginTop: "2vh",
        marginLeft: "10%",
        borderRadius: "10%",
        width: "80%"
    }
    const nomeOng={
        marginLeft:"42%",
        marginTop: "-17vh",
        display:"flex",
        fontSize:"40px",
        color:"white"
    }
    const slogan={
        marginLeft:"42%",
        display:"flex",
        fontSize:"20px",
        color:"white",
    }
    const alinhaBtn={
        width:"145%",
        marginLeft:"130%",
        paddingTop:"50%"
       
    }
    const estiloBtn={
        marginLeft:"12%",
        marginTop: "5%"
    }
    const btn = styled.button`
        color: ${({ theme }) => theme.body};
        box-shadow: 10px 10px 50px 10px rgba(255, 255, 255, 0.1);
    `;
    return(
        <>
            <Cartao>
                <DivImagem>
                    <img style={fotoFundo} src="http://guiadefontes.msf.org.br/wp-content/uploads/2017/01/org-actionaid-1024x443.jpg   " alt=""/>
                </DivImagem>
                <span style={nomeOng}>Action Aid<br /></span>
                <span style={slogan}>Você pode construir um mundo melhor!</span>
                <div style={fundoFotoPerfil} >
                    <DivLogin>
                        <img style={fotoPerfil} src="https://captadores.org.br/wp-content/uploads/2021/04/actionaid-logo-vector.png" width="170" height="170"/>
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
                </div>
            </Cartao>
        </>
    );
}

export default CartaoPerfilOng;