import React, { useEffect, useState } from 'react';
import SearchInput from './SearchInput';
import '../css/styles.css';
import styled from "styled-components";

export default function BarraPesquisa() {
  const [info, setInfo] = useState([]);
  const [text, setText] = useState("");
  const response = [];

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
          fetch(
            `http://localhost:8080/bp/doador/get/${text}`
          )
            .then((response) => response.json())
            .then((response) => {
              setInfo(response);
              console.log(response);
            })
            .catch((error) => {
              console.log('Catch:', error);
            });
        });
    }
  }, [text]);

  // const chamarPesquisa = (any) => {
  //   setText(any);

  //   // apiConsulta.get(`bp/ong/get/${text}`).then((dados) => {
  //   //   console.log(dados);
  //   // })
  // }

  const DivResult = styled.div`
     background-color: ${({ theme }) => theme.menu};
     width: 400px;
     height: 40px;
    `;

  return (
    <div className="divOng">
      <SearchInput
        value={text}
        onChange={(search) => setText(search)}
      />
      <br />{text && !info.length ? <h1>Não encontrado</h1> : info.map(item => (
        <DivResult key={item.cod}>
          {/*<div className="divImagem" style={{ backgroundImage: `url(data:image/jpeg;base64,${item.fotoPerfil}`, float: 'left' }}></div>
          <h1 className="h1Nome">{item.nome}</h1>*/}
          <table>
            <tr>
              <td className="divImagem" style={{ backgroundImage: `url(data:image/jpeg;base64,${item.fotoPerfil}`, float: 'left' }}></td>
              <td>{item.nome}</td>
            </tr>
          </table>
        </DivResult>
      ))}
    </div>
  );
}