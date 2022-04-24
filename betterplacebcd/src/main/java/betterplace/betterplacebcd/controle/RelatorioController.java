package betterplace.betterplacebcd.controle;

import betterplace.betterplacebcd.classes.DadosCsv;
import betterplace.betterplacebcd.classes.ListaObj;
import betterplace.betterplacebcd.classes.Media;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.classes.Forecast;
import betterplace.betterplacebcd.entidade.Vakinha;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import betterplace.betterplacebcd.repositorio.DoadorRepository;
import betterplace.betterplacebcd.repositorio.OngRepository;
import betterplace.betterplacebcd.repositorio.VakinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

@RestController
@RequestMapping("/doacao")
public class RelatorioController {

    @Autowired
    private VakinhaRepository vr;
    @Autowired
    private DoacoesRepository dcr;
    @Autowired
    private DoadorRepository dor;
    @Autowired
    private OngRepository or;
    private Forecast previsao = new Forecast();
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @GetMapping
    public ResponseEntity montarDados(){
        ListaObj<DadosCsv> dados = new ListaObj(vr.countByFkOng(1));
        for (Vakinha vakinha: vr.findByFkOng(1)){
            Object[] listaObj = new Object[12];
            listaObj[0] = vakinha.getNomeVakinha();
            listaObj[1] = vakinha.getNomeItem();
            listaObj[2] = vakinha.getDescVakinha();
            listaObj[3] = vakinha.getDataCriacao().format(formato);
            listaObj[4] = vakinha.getValorNecessario();
            Integer qtdeDoacoes = dcr.countByFkVakinha(vakinha.getIdVakinha());
            if(qtdeDoacoes!=null){
                listaObj[5] = 0;
            }
            else{
                listaObj[5] = qtdeDoacoes;
            }
            List<Doacao> listaDoacao =  dcr.findByFkVakinhaOrderByDataDoacaoDesc(vakinha.getIdVakinha());
            Double valorAtual = 0.0;
            if(!listaDoacao.isEmpty()){
                for (Doacao doacoes : listaDoacao){
                    valorAtual+=doacoes.getValorDoacao();
                }
                listaObj[6] = valorAtual;
                listaObj[7] = listaDoacao.get(0).getDataDoacao().format(formato);
                listaObj[8] = listaDoacao.get(0).getValorDoacao();
                listaObj[9] = dor.findByIdDoador(listaDoacao.get(0).getFkDoador()).get(0).getNomeDoador();
                Media media = new Media(vakinha.getDataCriacao().toLocalDate());
                listaObj[10] = media.calcularMedia(valorAtual);
                LocalDate dia = previsao.gerarForecast(listaDoacao, vakinha.getDataCriacao().toLocalDate(), vakinha.getValorNecessario(), valorAtual);
                if(dia!=null){
                    listaObj[11] = dia.format(formato);
                }
                else{
                    listaObj[11] = media.previsaoMedia(valorAtual, vakinha.getValorNecessario()).format(formato);
                }
            }
            else{
                listaObj[6] = valorAtual;
                listaObj[7] = "Não houve";
                listaObj[8] = 0.0;
                listaObj[9] = "";
                listaObj[10] = 0.0;
                listaObj[11] = "Não há dados";
            }

            dados.adiciona(new DadosCsv(listaObj));
        }
        String nome = "Relatorio_"+or.findByIdOng(1).get(0).getNomeOng()+"_"+ LocalDate.now();
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
                        dados.getNomeVakinha(), dados.getDescVakinha(), dados.getItemVakinha(), dados.getDataCriacao(),
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
