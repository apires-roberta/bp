import { blue } from "@mui/material/colors";
import styled from "styled-components";
import { Link, useNavigate } from "react-router-dom";
function CartaoPerfilOng(){
    const Cartao = styled.div`
        height:727px;
        margin:8%;
        width:65%;
        border-radius:14px;
        box-shadow: ${({ theme }) => theme.bordaFeed};
        background-color: ${({ theme }) => theme.body};
        color: ${({ theme }) => theme.logo};
        margin-left:17%;
    `;
        const DivLogin = styled.div`
        width: 77%;
        float: left;
        margin-left: 15%;
        margin-top:0%;
        border-radius:10px;
        background-color: ${({ theme }) => theme.body};
        color: #013]]]96F;
        height: 50vh;
        position:relative;
        z-index:99;
        box-shadow: ${({ theme }) => theme.bordaFeed};
        color: ${({ theme }) => theme.azulClaro};
        top: 8%;
    `;
    const Divtext = styled.div`
    width: 80%;
    height: 10vh;
    margin-top:30%;
        float: left;
        margin-left: 15%;
        font-size:25px;
    `;
    const DivImagem = styled.div`
    z-index:0;
    `;
    const fotoFundo={
        height: "32vh",
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
        marginLeft: "7vh",
        borderRadius: "10%",
    }
    const info={
        height: "30vh",
        width: "40vh",
        marginLeft: "8vh",
        marginBottom:"5%",
        fontSize:"100px",
        paddingTop:"2%",
    }
    const campanhaNumero={
        marginLeft:"44%",
    }
    const arrecadadoTotal={
        marginLeft:"19%",
        marginTop:"10%"
    }
    const nomeOng={
        marginLeft:"40%",
        marginTop: "-17vh",
        display:"flex",
        fontSize:"40px",
        color:"white"
    }
    const slogan={
        marginLeft:"40%",
        display:"flex",
        fontSize:"20px",
        color:"white",
    }
    const alinhaBtn={
        width:"145%",
        marginLeft:"130%",
        paddingTop:"66%"
       
    }
    const campanhaTexto = {
        marginLeft:"12%"
    }
    const alinhaCard={
        marginTop:"18vh",
    }
    const estiloBtn={
        marginLeft:"12%"
    }
    const btn = styled.button`
    color: ${({ theme }) => theme.body};
    box-shadow: 10px 10px 50px 10px rgba(255, 255, 255, 0.1);
    `;
    return(
        <>
        <Cartao>
            <DivImagem>
         <img style={fotoFundo} src="https://www.handsoflove.org.za/wp-content/uploads/slider/cache/d9b7d38169833bba112a47b25d718e46/home-header.jpg" alt=""/>
            </DivImagem>
         <span style={nomeOng}> Amigos da Rua<br /></span>
         <span style={slogan}> Foco na ajuda de pessoas em situação de rua</span>
         <div style={fundoFotoPerfil} >
         <DivLogin>
          <img style={fotoPerfil} src="https://storage.googleapis.com/atados-v3/user-uploaded/images/3ab557e4-bf6f-433a-9e00-3992580b416a.jpg" width="170" height="170"/>
          <Divtext>
            <span style={campanhaTexto}> Campanhas:<br></br></span><span style={campanhaNumero}>5</span><br /><br />
           <span>Total arrecadado:</span> <span style={arrecadadoTotal}>   R$1500,00</span>
            </Divtext>
          </DivLogin>
          <div style={alinhaBtn}>
            <Link to="/campanhas-doador"><button style={estiloBtn} class="btn">Campanha</button><br /><br /><br /></Link>
            <Link to="/feed"><button style={estiloBtn} class="btn">Feed</button><br /><br /><br /></Link>
            <Link to="/dashboard"><button style={estiloBtn} class="btn">Dashboard</button></Link>
          </div>
          </div>
          <div style={info}>
          </div>
          <div style={alinhaCard}></div>
         </Cartao>
        </>
    );
}

export default CartaoPerfilOng;