import estrela from '../img/estrela.png'
function CardDados(props){
    const teste={
        marginTop:"6%",
        marginBottom:"0%"
    };
    return(
        <div className="info" style={teste}>
            <h2>{props.nome}</h2>
            <p>{props.descricao}</p><br/>
            <img class="img-estrela" src={estrela} alt=""/>
            <h3>R${props.valorCampanha}</h3>
        </div>
    );
}

export default CardDados;