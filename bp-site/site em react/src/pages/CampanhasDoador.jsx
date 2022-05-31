import CardCampanhaDoador from "../components/CardCampanhaDoador";
import Menu from "../components/Menu";

function CampanhasDoador() {

  return (
    <>
    <Menu/>
    <div class="div-campanhas">               
      <CardCampanhaDoador nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00}/>
      <CardCampanhaDoador nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00}/>
      <CardCampanhaDoador nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00}/>
      <CardCampanhaDoador nome="Juan" descricao="texto para chegar nos 100 caracteres pois quero testar como vai ficar chegando na quantidade maxima." valorCampanha={1000.00}/>
    </div>
    </>
  );
}

export default CampanhasDoador;
