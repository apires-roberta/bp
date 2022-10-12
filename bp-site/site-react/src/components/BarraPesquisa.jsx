import React, { useEffect, useState } from 'react';
import SearchInput from './SearchInput';
import '../css/styles.css';
import styled from "styled-components";

export default function BarraPesquisa() {
  const [info, setInfo] = useState([]);
  const [text, setText] = useState("");
  const element = "";

  useEffect(() => {
    if (text) {
      fetch(
        `http://localhost:8080/bp/ong/nomeOng?nomeOng=${text}`
      )
        .then((response) => response.json())
        .then((response) => {
          setInfo(response);
          console.log(response);
        })
        .catch((error) => {
          /*fetch(
            `http://localhost:8080/bp/doador/get/${text}`
          )
            .then((response) => response.json())
            .then((response) => {
              setInfo(response);
              console.log(response);
            })
            .catch((error) => {
              console.log('Catch:', error);
              element = <h1>Não encontrado</h1>;
            });*/
        });
    }
    
  }, [text]);

  const DivResult = styled.table`
     background-color: ${({ theme }) => theme.menu};
     width: 400px;
     height: 40px;
    `;

  const TdEstilo = styled.td`
    @import url(https://fonts.googleapis.com/css2?family=Comfortaa:wght@300;400&display=swap);
    color: ${({ theme }) => theme.logo};
    text-align: left;
    width: 280px;
    font-size: 25px;
    font-family: 'Comfortaa', cursive;
    padding-left: 15px;
   `;

  return (
    <div className="divOng">
      <SearchInput
        value={text}
        onChange={(search) => setText(search)}
      />
      <br />{text && info.length ?  info.map(item => (
        <DivResult key={item.cod}>
          {/*<div className="divImagem" style={{ backgroundImage: `url(data:image/jpeg;base64,${item.fotoPerfil}`, float: 'left' }}></div>
          <h1 className="h1Nome">{item.nome}</h1>*/}
            <tr className="tableImg">
              <td ><div className="divImagem" style={{ backgroundImage: `url(data:image/jpeg;base64,${item.fotoPerfil}`, float: 'left' }}></div><br /></td>
              <TdEstilo>{item.nome}</TdEstilo>
            </tr>
        </DivResult>
      )): <h1>{element}</h1>}
    </div>
  );
}