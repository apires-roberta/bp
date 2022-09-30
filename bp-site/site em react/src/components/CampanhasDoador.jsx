import CardCampanhaDoador from "../components/CardCampanhaDoador";
import React, { useState, useEffect } from "react";
import styled from "styled-components";
import apiCampanha from "../apiCampanha";

function CampanhasDoador() {
  const DivInfo = styled.div`
      width: 80%;
      margin-top: 4%;
      margin-left: 3%;
      overflow:auto;
      padding:5%;
    `;

  const [campanha, setcampanha] = useState([]);

  useEffect(() => {
    apiCampanha.get("/campanha/").then((resposta) => {
      if (resposta.status === 200) {
        setcampanha(resposta.data)
      }
    })
  }, [])
  
  return (
    <>
          <DivInfo>
            {
              campanha.map((item) => (
                <CardCampanhaDoador id={item.ongCod}
                descCampanha={item.descCampanha}
                valorCampanha={item.valorNecessario}
                nome={item.nomeCampanha}
                campanha={item.idCampanha}
                />
              ))
            }
          </DivInfo>
    </>
  );
}

export default CampanhasDoador;
