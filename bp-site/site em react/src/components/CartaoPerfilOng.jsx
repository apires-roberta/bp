import styled from "styled-components";
function CartaoPerfilOng(){
    const Cartao = styled.div`
        border: 1px solid white;
        heigth: 200px;
        margin: 8%;
        box-shadow: ${({ theme }) => theme.bordaFeed};
        background-color: ${({ theme }) => theme.body};
        color: ${({ theme }) => theme.logo};
    `;
    const fotoFundo={
        height: "22vh",
        width: "100%",
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
        borderRadius: "50%",
    }
    const info={
        height: "30vh",
        width: "40vh",
        marginLeft: "8vh",
        marginBottom:"5%",
        fontSize:"18px",
        paddingTop:"2%",
    }
    const alinhaFonte={
        marginLeft:"4%",
    }
    const campanhaNumero={
        marginLeft:"4%",
    }
    const arrecadadoTotal={
        marginLeft:"4%",
    }
    const nomeOng={
        marginLeft:"40%",
        marginTop: "-12vh",
        display:"flex",
        fontSize:"25px",
        color:"white"
    }
    const slogan={
        marginLeft:"40%",
        display:"flex",
        fontSize:"15px",
        color:"white"
    }
    const alinhaBtn={
        width:"50%",
        marginLeft:"40%",
        marginTop: "-50vh",
    }
    const alinhaCard={
        marginTop:"18vh",
    }
    const estiloBtn={
        marginLeft:"28%"
    }
    const btn = styled.button`
    color: ${({ theme }) => theme.body};
    box-shadow: 10px 10px 50px 5px rgba(255, 255, 255, 0.1);
    `;
    return(
        <>
        <Cartao>
         <img style={fotoFundo} src="https://www.w3schools.com/css/pineapple.jpg" alt=""/>
         <span style={nomeOng}>Nome Ong <br /></span>
         <span style={slogan}> Foco na ajuda de pessoas em situação de rua</span>
         <div style={fundoFotoPerfil} >
          <img style={fotoPerfil} src="https://www.w3schools.com/css/pineapple.jpg" width="170" height="170"/>
          </div>
          <div style={info}>
            <br /><br />
           <span style={alinhaFonte}> Campanhas:</span><span style={campanhaNumero}>5</span><br /><br /><br />
           <span style={alinhaFonte}>Total arrecadado: </span> <span style={arrecadadoTotal}>1500</span>
          </div>
          <div style={alinhaBtn}>
            <button style={estiloBtn} class="btn">Campanha</button><br /><br /><br />
            <button style={estiloBtn} class="btn">Feed</button><br /><br /><br />
            <button style={estiloBtn} class="btn">Dashboard</button>
          </div>
          <div style={alinhaCard}></div>
         </Cartao>
        </>
    );
}

export default CartaoPerfilOng;