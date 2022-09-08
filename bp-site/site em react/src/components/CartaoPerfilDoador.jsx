import { blue } from "@mui/material/colors";
import styled from "styled-components";
function CartaoPerfilDoador(){
    const Cartao = styled.div`
        height:727px;
        margin:8%;
        width:65%;
        border-radius:10px;
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
        background-color:${({ theme }) => theme.body};
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
    margin-top:10%;
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
        marginTop: "0vh",
        marginLeft: "0vh",
        borderRadius: "3%",
        width:"100%",
        height:"50%"
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
        marginLeft:"43%",
    }
    const arrecadadoTotal={
        marginLeft:"32%",
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
        marginLeft:"5%"
    }
    const campanhaTextoP = {
        marginLeft:"15%"
    }
    const alinhaCard={
        marginTop:"18vh",
    }
    const titulosCartao={
        fontSize:"22px"
    }
    const estiloBtn={
        marginLeft:"25%"
    }
    const imgMedalhas={
        width:"25%"
    }
    const btn = styled.button`
    color: ${({ theme }) => theme.body};
    box-shadow: 10px 10px 50px 10px rgba(255, 255, 255, 0.1);
    `;
    return(
        <>
        <Cartao>
            <DivImagem>
         <img style={fotoFundo} src="https://f8n-production.s3.amazonaws.com/creators/profile/rf7rdju95-237-2376520-twitter-header-wallpapers-minimalist-dual-monitor-wallpaper-4k-jpg-cqlv2j.jpg" alt=""/>
            </DivImagem>
         <span style={nomeOng}>Marina Ferraz<br /></span>
         <span style={slogan}>Professora, 41 anos</span>
         <div style={fundoFotoPerfil} >
         <DivLogin>
          <img style={fotoPerfil} src="https://images03.brasildefato.com.br/d753690c552a7a06b95d7b17ea689f06.jpeg"/>
          <Divtext>
            <span style={campanhaTexto}> Colaborações:<br></br></span><span style={campanhaNumero}>5</span><br /><br />
           <span style={campanhaTextoP}>Pontuação:</span> <span style={arrecadadoTotal}>2534</span>
            </Divtext>
          </DivLogin>
          <div style={alinhaBtn}>
            <span style={titulosCartao}> Bio:</span><br /><br /><span>"Sou mãe e casada, gosto muito de poder ajudar as pessoas da forma que posso!"</span><br /><br /><br />
            <span style={titulosCartao}> Medalhas:</span><br /><br />
            <img style={imgMedalhas} src="https://cdn-icons-png.flaticon.com/512/2583/2583381.png"/><img style={imgMedalhas} src="https://cdn-icons-png.flaticon.com/512/2583/2583350.png"/> <img style={imgMedalhas} src="https://cdn-icons-png.flaticon.com/512/2583/2583448.png"/>
          </div>
          </div>
          <div style={info}>
          </div>
          <div style={alinhaCard}></div>
         </Cartao>
        </>
    );
}

export default CartaoPerfilDoador;