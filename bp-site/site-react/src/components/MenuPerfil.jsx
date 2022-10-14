import styled from "styled-components";
function MenuPerfil(){
    const P = styled.p`
        padding: 1vh;
        border: solid 1px #CCCCCC;
        cursor:pointer;
        color: ${({ theme }) => theme.logo};
    `;

    const Div = styled.div`
        background-color: ${({ theme }) => theme.body};
        width: 20%;
        z-index:100;
        float: left;
        margin-left: 75%;
        text-align: center;
        display: none;
        box-shadow: 10px 10px 50px 5px rgba(0, 0, 0, 0.2);
    `;
    return (
        <>
           <Div onMouseEnter={manterMenu} onMouseLeave={fecharMenu} id={"divMenu"}>
               <P>Minha conta</P>
               <P>Alterar dados cadastrais</P>
               <P>Alterar dados do perfil</P>
               <P>Logout</P>
           </Div>
        </>
    );
}

export default MenuPerfil;

function manterMenu(){
    document.getElementById("divMenu").style.display="block";
}

function fecharMenu(){
    document.getElementById("divMenu").style.display="none";
}