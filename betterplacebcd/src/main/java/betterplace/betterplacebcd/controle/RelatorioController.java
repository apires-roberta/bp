package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.DadosCsv;
import betterplace.betterplacebcd.classes.ListaObj;
import betterplace.betterplacebcd.classes.Media;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.classes.Forecast;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.entidade.Doador;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import betterplace.betterplacebcd.repositorio.DoadorRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        String csv = gravaArquivoCsv(dados, nome);
        return ResponseEntity.status(200).header("content-type", "text/csv")
                .header("content-disposition", "filename=\""+nome+".csv\"")
                .body(csv);
    }

    public String gravaArquivoCsv(ListaObj<DadosCsv> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        nomeArq += ".csv";
        String formatado="";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo!");
            System.exit(1);
        }

        try {
            for (int i= 0; i< lista.getTamanho(); i++) {
                DadosCsv dados = lista.getElemento(i);
                formatado += String.format("%s;%s;%s;%s;%.2f;%.2f;%d;%s;%s;%.2f;%.2f;%s\n",
                        dados.getNomeCampanha(), dados.getDescCampanha(), dados.getItemCampanha(), dados.getDataCriacao(),
                        dados.getValorNecessario(), dados.getValorAtual(), dados.getQtdDoacoes(), dados.getNomeDoador(),
                        dados.getDataDoacao(), dados.getValorDoacao(), dados.getMedia(), dados.getDataPrevisao());
                saida.format(formatado);
            }
        }
        catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar arquivo");
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
        return formatado;
    }

}
