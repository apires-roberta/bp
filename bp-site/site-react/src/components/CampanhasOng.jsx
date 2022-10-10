import CardCampanhaOng from "./CardCampanhaOng";
import React, { useState, useEffect } from "react";
import styled from "styled-components";
import BasicModal from "./Modal";
import apiCampanha from "../apiCampanha";

function CampanhasOng() {
  const [campanha, setcampanha] = useState([]);
  const DivInfo = styled.div`
      width: 80%;
      margin-top: 10%;
      margin-left: 8%;
      height: auto;
      overflow:hidden;
    `; 

  useEffect(() => {
    apiCampanha.get(`/campanha/Ong/${sessionStorage.getItem("idOng")}/`).then((resposta) => {
      console.log(resposta)
      if (resposta.status === 200) {
        setcampanha(resposta.data)
      }
    })
  }, [])

  return (
    <>
          <DivInfo>{
            campanha.map((item) => (
              <CardCampanhaOng nome={item.nomeCampanha}
                descCampanha={item.descCampanha}
                valorCampanha={item.valorNecessario}
                id={item.idCampanha} validacao={item.disponivel} />

            ))
          }
            <BasicModal />
          </DivInfo>
    </>
  );
}

export default CampanhasOng;

