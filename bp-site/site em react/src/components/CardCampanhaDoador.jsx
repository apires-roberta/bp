import estrela from '../img/estrela.png'
function CardCampanhaDoador(props){

      return(
        <>
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
            <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap" rel="stylesheet"/>
            <div class="info">
                <h2>{props.nome}</h2>
                <p>{props.descricao}</p><br/>
                <img class="img-estrela" src={estrela} alt=""/>
                <h3>R${props.valorCampanha}</h3>
            </div>
        </>
      );
  }
  
  export default CardCampanhaDoador;