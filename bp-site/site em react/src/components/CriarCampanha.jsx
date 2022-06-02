import styled from "styled-components";
function CriarCampanha(props) {
    const DivInfo = styled.div`
        width: 24.5%;
        margin-left: 8%;
        margin-bottom: 5%;
        float: left;
        box-shadow: ${({ theme }) => theme.borda};
        border-radius: 10px;
        color: ${({ theme }) => theme.logo};
        border: ${({ theme }) => theme.bordaInfo} 2px solid;
        height: 37vh;
        text-align: center;
    `;

    const estiloDiv = {
        marginBottom: "5%"
    };

    const estiloP = {
        fontSize:"160px",
        paddingTop: "22%"
    };
    return (
        <>
            <DivInfo style={estiloDiv}>
                <p style={estiloP}>+</p>
            </DivInfo>
        </>
    );
}

export default CriarCampanha;