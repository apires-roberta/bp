import estrela from '../img/estrela.png'
function CardCampanhaOng(props){

      return(
          <>
          
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap" rel="stylesheet"/>
        <div class="container-campanha">
        <div class="div-nome">
         <span class="nome-ong" >{props.nome}</span><br/>
        </div>
        <div class="div-descricao">
         <span class="descricao-campanha">{props.descricao}</span><br/>
        </div>
        <div class="div-img">
         <img class="img-estrela" src={estrela} alt=""/>
        </div>
        <div class="div-valor">
         <span class="valor-campanha">R$</span><span class="valor-campanha">{props.valorCampanha}</span>
        </div>
       </div>
       </>
      );
  }
  
  export default CardCampanhaOng;