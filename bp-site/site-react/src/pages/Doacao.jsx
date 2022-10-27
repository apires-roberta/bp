import { Grafico } from "../components/Grafico";
import Menu from "../components/Menu";
import BotaoDoacao from "../components/BotaoDoacao";
import send from "../img/send.png"
import { ThemeProvider } from "styled-components";
import { lightTheme, darkTheme } from "../theme";
import GlobalTheme from "../globals";
import styled from "styled-components";
import React, { Fragment, useState, useEffect } from "react";
import CardDados from "../components/CardDados";
import apiCampanha from "../apiCampanha";
import CardCampanhaDoador from "../components/CardCampanhaDoador";
import Rodape from "../components/Rodape";
import ip from '../ip';

function redirecionar(pagina) {
  window.location.href = `http://${ip}:3000/` + pagina;
}

function Doacao() {

  const DivInfo = styled.div`
      width: 80%;
      margin-left: 3%;
      overflow:auto;
      padding:5%;
    `;

  const funcData = useState({
    idDoador: "",
    idCampanha: "",
    valorDoacao: ""
  })
  function doar() {
    var codigo = sessionStorage.getItem("idDoador")
    var valor = document.getElementById("valores").value;
    if (codigo == null) {
      redirecionar("login")
    }
    else {
      funcData.idDoador = codigo;
      funcData.idCampanha = sessionStorage.getItem("campanha");
      funcData.valorDoacao = valor;
      apiCampanha.post("/doacao", {
        idDoador: funcData.idDoador,
        idCampanha: funcData.idCampanha,
        valorDoacao: funcData.valorDoacao
      }).then((resposta) => {
        console.log("post ok", resposta);
        redirecionar("NotaFiscal")
      })
    }
  }

  const [theme, setTheme] = useState("light");

  const toggleTheme = () => {
    if (theme === "light") {
      window.localStorage.setItem("theme", "dark");
      setTheme("dark");
    } else {
      window.localStorage.setItem("theme", "light");
      setTheme("light");
    }
  };
  useEffect(() => {
    const localTheme = window.localStorage.getItem("theme");
    localTheme && setTheme(localTheme);
  }, []);

  const [campanha, setcampanha] = useState([]);
  const [recomendados, setRecomendados] = useState([]);

  useEffect(() => {
    apiCampanha.get(`/campanha/${sessionStorage.getItem("campanha")}`).then((resposta) => {
      console.log(resposta )
      if (resposta.status === 200) {  
        setcampanha(resposta.data)
      }
    })
  }, [])

  useEffect(()=>{
    apiCampanha.get(`/campanha/recomendacao/${sessionStorage.getItem("campanha")}`).then((resposta) => {
      console.log(resposta )
      if (resposta.status === 200) {  
        console.log(resposta.data)
        setRecomendados(resposta.data)
      }
    })
  },[])

  const DivValor = styled.div`
      margin-top: 10%;
      float: left;
      color: ${({ theme }) => theme.logo};
      margin-left: 10%;
    `;
  const InputValor = styled.input`
      float: left;
      margin-left: 1%;
      border: none;
      border-bottom: 2px solid ${({ theme }) => theme.logo};
      margin-top: 3%;
      width: 80%;
      background-color: ${({ theme }) => theme.body};
      color:  ${({ theme }) => theme.logo};
    `;
  const estiloDiv = {
    marginLeft: "14%"
  }
  console.log(campanha.totalDoado)
  if(sessionStorage.getItem('tipo')==="PerfilDoador")
  return (
    <>
      <Menu funcaoDark={toggleTheme} />
      <ThemeProvider theme={theme === "light" ? lightTheme : darkTheme}>
        <Fragment>
          <GlobalTheme />
          <div className="teste">
            <CardDados style={estiloDiv}
            id={sessionStorage.getItem("campanha")} />
            <div className="grafico">
              <Grafico valorDesejado={campanha.valorNecessario} valorAtual={Number(campanha.totalDoado)}/>
            </div>
          </div>

          <div className="botoesDoacoes">
            <BotaoDoacao valor={10} />
            <BotaoDoacao valor={20} />
            <BotaoDoacao valor={30} />
            <BotaoDoacao valor={40} />
            <BotaoDoacao valor={50} />
            <div className="botaoOutro">
              <DivValor className="outroValor">
                <span>Outro valor:</span><br />
                <p>R$</p><InputValor id="valores" type="text" />
              </DivValor>
              <button onClick={doar}><img className="enviarValor" src={send} alt='' /></button>
            </div>
          </div>
          <DivInfo>
          {
              recomendados.map((item) => (
                <CardCampanhaDoador id={item.ongCod}
                descCampanha={item.descCampanha}
                valorCampanha={item.valorNecessario}
                nome={item.nomeCampanha}
                campanha={item.idCampanha}
                
                />
              ))
            }
            </DivInfo>
            <Rodape/>
        </Fragment>
      </ThemeProvider>
    </>
  );
  else{
    redirecionar("Perfil")
  }
}

export default Doacao;