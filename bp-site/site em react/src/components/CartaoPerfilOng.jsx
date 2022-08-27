import styled from "styled-components";
function CartaoPerfilOng(){
    const Cartao = styled.div`
        border: 3px solid #4CAF50;
        heigth: 200px;
        margin: 8%;
        box-shadow: ${({ theme }) => theme.bordaFeed};
        background-color: ${({ theme }) => theme.body};
        color: ${({ theme }) => theme.logo};
    `;
    const fotoFundo={
        height: "20vh",
        border: "3px solid #4c5eaf",
        width: "100%",
    }
    const fundoFotoPerfil={
        backgroundColor: "darkcyan",
        border: "3px solid #4c5eaf",
        height: "30vh",
        width: "40vh",
        marginTop: "-16vh",
        marginLeft: "8vh",
        
    }
    const fotoPerfil= {
        marginTop: "2vh",
        marginLeft: "7vh",
        borderRadius: "50%"
    }
    const info={
        border: "3px solid #af4c96",
        height: "30vh",
        width: "40vh",
        marginLeft: "8vh",
    }
    return(
        <>
        <Cartao>
         <img style={fotoFundo} src="https://www.w3schools.com/css/pineapple.jpg" alt=""/>
         <div style={fundoFotoPerfil} >
          <img style={fotoPerfil} src="https://www.w3schools.com/css/pineapple.jpg" width="170" height="170"/>
           nome
          </div>
          <div style={info}>
           Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet...
          </div>
         </Cartao>
        </>
    );
}

export default CartaoPerfilOng;