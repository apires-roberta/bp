function Input(props) {
    return (
        <>
            <div className="inputDiv">
                <span class="nomeInput">{props.nome}</span><br/>
                <input type={props.tipo} id={props.id} class="input"/>
            </div>
        </>
    );
  }
  
  export default Input;