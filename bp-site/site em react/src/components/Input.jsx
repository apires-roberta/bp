import styled from "styled-components";

function Input(props) {
    const InputStyle = styled.input`
        border-radius: 30px;
        height: 5vh;
        width: 60%;
        margin-left: 20%;
        text-align: center;
        background-color: transparent;
        border: ${({ theme }) => theme.bordaInput} 2px solid;
        color: ${({ theme }) => theme.letraInput};
    `;
    return (
        <>
            <div className="inputDiv">
                <span className="nomeInput">{props.nome}</span><br/>
                <InputStyle type={props.tipo} id={props.id}/>
            </div>
        </>
    );
  }
  
  export default Input;