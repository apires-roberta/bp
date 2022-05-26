package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.*;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.entidade.Ong;
import betterplace.betterplacebcd.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private CampanhaRepository vr;
    @Autowired
    private DoacoesRepository dcr;
    @Autowired
    private DoadorRepository dor;
    @Autowired
    private OngRepository or;
    private Forecast previsao = new Forecast();
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private GravarArquivo ga = new GravarArquivo();
    private ExportarDados ed = new ExportarDados();

    @GetMapping("/csv/{cod}")
    public ResponseEntity montarDados(@PathVariable Integer cod){
        if(or.findByCod(cod).isEmpty()){
            return ResponseEntity.status(404).build();
        }
        if(vr.findByFkOng(cod).isEmpty()){
            return ResponseEntity.status(204).build();
        }
        ListaObj<DadosCsv> dados = new ListaObj(vr.countByFkOng(cod));
        for (Campanha campanha: vr.findByFkOng(cod)){
            Object[] listaObj = new Object[12];
            listaObj[0] = campanha.getNomeCampanha();
            listaObj[1] = campanha.getNomeItem();
            listaObj[2] = campanha.getDescCampanha();
            listaObj[3] = campanha.getDataCriacao().format(formato);
            listaObj[4] = campanha.getValorNecessario();
            Integer qtdeDoacoes = dcr.countByFkCampanha(campanha.getIdCampanha());
            if(qtdeDoacoes!=null){
                listaObj[5] = 0;
            }
            else{
                listaObj[5] = qtdeDoacoes;
            }
            List<Doacao> listaDoacao =  dcr.findByFkCampanhaOrderByDataDoacaoDesc(campanha.getIdCampanha());
            Double valorAtual = 0.0;
            if(!listaDoacao.isEmpty()){
                for (Doacao doacoes : listaDoacao){
                    valorAtual+=doacoes.getValorDoacao();
                }
                listaObj[6] = valorAtual;
                listaObj[7] = listaDoacao.get(0).getDataDoacao().format(formato);
                listaObj[8] = listaDoacao.get(0).getValorDoacao();
                List<Doador> doador = dor.findByCod(listaDoacao.get(0).getFkDoador());
                if(doador.isEmpty()){
                    listaObj[9] = "Usuario nao esta mais no site";
                }
                else{
                    listaObj[9] = doador.get(0).getNome();
                }
                Media media = new Media(campanha.getDataCriacao().toLocalDate());
                listaObj[10] = media.calcularMedia(valorAtual);
                LocalDate dia = previsao.gerarForecast(listaDoacao, campanha.getDataCriacao().toLocalDate(), campanha.getValorNecessario(), valorAtual);
                System.out.println(dia);
                if(dia!=null){
                    listaObj[11] = dia.format(formato);
                }
                else{
                    listaObj[11] = media.previsaoMedia(valorAtual, campanha.getValorNecessario()).format(formato);
                }
            }
            else{
                listaObj[6] = valorAtual;
                listaObj[7] = "Não houve";
                listaObj[8] = 0.0;
                listaObj[9] = "Não há dados";
                listaObj[10] = 0.0;
                listaObj[11] = "Não há dados";
            }

            dados.adiciona(new DadosCsv(listaObj));
            Integer tamanho = dados.getTamanho();
            for(int i=0; i<tamanho-1;i++){
                Double menor = dados.getElemento(i).getValorNecessario();
                int posicao = 0;
                for(int j=i+1; j<tamanho;j++){
                    if(menor>dados.getElemento(j).getValorNecessario()){
                        posicao = j;
                    }
                }
                if(posicao>0){
                    dados.ordenar(i,posicao,dados.getElemento(i));
                }
            }
        }
        String nome = "Relatorio_"+or.findByCod(cod).get(0).getNome()+"_"+ LocalDate.now();
        String csv = ga.gravaArquivoCsv(dados, nome);
        return ResponseEntity.status(200).header("content-type", "text/csv")
                .header("content-disposition", "filename=\""+nome+".csv\"")
                .body(csv);
    }

    @GetMapping("/Exportacao/{cod}")
    public ResponseEntity exportarDados(@PathVariable Integer cod){
        String nomeOng;
        String nomeCampanha, itemCampanha, descCampanha, nomeDoador="";
        Double valorNecessario, valorAtual, valorDoacao=0.0, media=0.0;
        Integer qtdDoacoes;
        LocalDate dataCriacao, dataDoacao=null, dataPrevisao=null;
        List<Dados> dados = new ArrayList();
        List<Ong> listaOng = or.findByCod(cod);
        if(listaOng.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        else{
            nomeOng = listaOng.get(0).getNome();
        }
        for (Campanha campanha: vr.findByFkOng(cod)){
            nomeCampanha = campanha.getNomeCampanha();
            itemCampanha = campanha.getNomeItem();
            descCampanha = campanha.getDescCampanha();
            valorNecessario = campanha.getValorNecessario();
            dataCriacao = campanha.getDataCriacao().toLocalDate();
            Integer qtdeDoacoes = dcr.countByFkCampanha(campanha.getIdCampanha());
            if(qtdeDoacoes!=null){
                qtdDoacoes = 0;
            }
            else{
                qtdDoacoes = qtdeDoacoes;
            }
            List<Doacao> listaDoacao =  dcr.findByFkCampanhaOrderByDataDoacaoDesc(campanha.getIdCampanha());
            valorAtual = 0.0;
            if(!listaDoacao.isEmpty()){
                for (Doacao doacoes : listaDoacao){
                    valorAtual+=doacoes.getValorDoacao();
                }
                dataDoacao = listaDoacao.get(0).getDataDoacao().toLocalDate();
                valorDoacao = listaDoacao.get(0).getValorDoacao();
                List<Doador> doador = dor.findByCod(listaDoacao.get(0).getFkDoador());
                if(doador.isEmpty()){
                    nomeDoador = "Usuario nao esta mais no site";
                }
                else{
                    nomeDoador = doador.get(0).getNome();
                }
                Media m = new Media(campanha.getDataCriacao().toLocalDate());
                media = m.calcularMedia(valorAtual);
                LocalDate dia = previsao.gerarForecast(listaDoacao, campanha.getDataCriacao().toLocalDate(), campanha.getValorNecessario(), valorAtual);
                if(dia!=null){
                    dataPrevisao = dia;
                }
                else{
                    dataPrevisao = m.previsaoMedia(valorAtual, campanha.getValorNecessario());
                }
            }
            dados.add(new Dados(nomeCampanha,itemCampanha, descCampanha, dataCriacao, dataDoacao, nomeDoador, dataPrevisao,
                                valorNecessario, valorAtual, valorDoacao, media, qtdDoacoes));
        }
        return ResponseEntity.status(200).header("content-type", "text/csv")
                .header("content-disposition", "filename=\"Relatorio_exportacao_"+nomeOng+".txt\"")
                .body(ed.gravaArquivoTxt(dados, nomeOng));
    }

    @PatchMapping(value = "/foto/{codigo}", consumes="image/jpeg")
    public ResponseEntity patchFoto(@PathVariable long codigo, @RequestBody byte[] novaFoto){
        return ResponseEntity.status(200).body(novaFoto);
    }
}
