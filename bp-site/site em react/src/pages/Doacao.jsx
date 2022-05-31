import { Grafico } from "../components/Grafico";
import Menu from "../components/Menu";
import CardDados from "../components/CardDados";
import BotaoDoacao from "../components/BotaoDoacao";
import send from "../img/send.png"
import api from "../api";
import { useEffect, useState } from "react";
function Doacao() {
    const [musicas, setMusicas] = useState([]);
      useEffect(()=>{
        api.get("/doacao/valor/1").then((resposta)=>{
          console.log(resposta.data)
          setMusicas(resposta.data)
        })
      },[])
    return (
        <>
            <Menu/>
            <CardDados/>
            <div className="grafico">
                <Grafico valorDesejado={100} valorAtual={20}/>
            </div>
            <div className="botoesDoacoes">
                <BotaoDoacao valor={10}/>
                <BotaoDoacao valor={20}/>
                <BotaoDoacao valor={30}/>
                <BotaoDoacao valor={40}/>
                <BotaoDoacao valor={50}/>
                <div className="botaoOutro">
                <div className="outroValor">
                    <span>Outro valor:</span><br/>
                    <p>R$</p><input id="valores" type="text"/>
                </div>
                <button><img className="enviarValor" src={send}/></button>
                </div>
            </div>
            
        </>
    );
  }
  
  export default Doacao;