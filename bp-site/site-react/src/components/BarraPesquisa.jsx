import React, { useEffect, useState } from 'react';
import SearchInput from './SearchInput';
import '../css/styles.css';
import styled from "styled-components";
import { Link } from "react-router-dom";
import ipBack from "../ipBack";

export default function BarraPesquisa() {
  const [info, setInfo] = useState([]);
  const [text, setText] = useState("");
  const element = "";

  useEffect(() => {
    if (text) {
      fetch(
        `http://${ipBack}:8080/bp/ong/nomeOng?nomeOng=${text}`
      )
        .then((response) => response.json())
        .then((response) => {
          setInfo(response);
          console.log(response);
          
        })
        .catch((error) => {
          /*fetch(
            `http://52.0.52.140:8080/bp/doador/get/${text}`
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
    border: 0px;
   `;

   const estiloPesquisa={
    marginLeft: "13%"
   }

  return (
    <div className="divOng" style={estiloPesquisa}>
      <SearchInput
        value={text}
        onChange={(search) => setText(search)}
      />
      <br />{text && info.length ?  info.map(item => (
        <DivResult key={item.cod}>
          {sessionStorage.setItem('cod',`${item.cod}`)}
            <tr className="tableImg">
            <Link to="/PerfilResultado" >
              <td ><img className="divImagem" src={item.fotoPerfil} alt="" /><br /></td>
            </Link><td>
            <Link to="/PerfilResultado" className='link'>  
              <TdEstilo>{item.nome}</TdEstilo> 
            </Link>
            </td>
            </tr>
        </DivResult>
      )): <h1>{element}</h1>}
    </div>
  );
}