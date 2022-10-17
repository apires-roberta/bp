import apiCampanha from "./apiCampanha";
import apiLogin from "./apiLogin";
var dadosDoador=[];
var dadosOng=[];
var dadosDoacao=[];
var dadosCampanha=[];
var dados = [];
    apiCampanha.get(`/dash/${"SP"}`).then((resposta) => {
        var vetor = resposta.data;
        for(var i=0; i<vetor.length; i++){
            if(vetor[i][0]=="Doador"){
                dadosDoador.push([vetor[i][1], vetor[i][2]]);
            }
            else if(vetor[i][0]=="Ong"){
                dadosOng.push([vetor[i][1], vetor[i][2]]);
            }
            else if(vetor[i][0]=="Doacao"){
                dadosDoacao.push([vetor[i][1], vetor[i][2]]);
            }
            else if(vetor[i][0]=="Campanha"){
                dadosCampanha.push([vetor[i][1], vetor[i][2]]);
            }
        }
        dados.push({"Doador": dadosDoador, "Ong":dadosOng, "Doacao":dadosDoacao, "Campanha":dadosCampanha})
    });
export default dados;