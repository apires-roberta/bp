import { useEffect, useState } from "react";
import CardCampanhaDoador from "../components/CardCampanhaDoador";
import MenuDoador from "../components/MenuDoador";
import api from "../api";

function CampanhasDoador() {

  const [musicas, setMusicas] = useState([]);

  useEffect(() => {
    api.get("/").then((resposta) => {
      console.log(resposta.data)
      setMusicas(resposta.data)
    })
  },[])

  return (
    <>
      <MenuDoador/>
      <div class="div-campanhas">
        {
          musicas.map((item) => (
            <CardCampanhaDoador nome={item.nome}
              descricao={item.descricao}
              valorCampanha={item.valorCampanha} />
          ))
        }
      </div>
    </>
  );
}

export default CampanhasDoador;
