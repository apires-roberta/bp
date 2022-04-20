package com.example.ApiCsv.controle;

import com.example.ApiCsv.entidade.Doacao;
import com.example.ApiCsv.entidade.Forecast;
import com.example.ApiCsv.entidade.Vakinha;
import com.example.ApiCsv.repositorio.doacoesRepository;
import com.example.ApiCsv.repositorio.doadorRepository;
import com.example.ApiCsv.repositorio.ongRepository;
import com.example.ApiCsv.repositorio.vakinhaRepository;
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
public class DoacoesController {

    @Autowired
    private vakinhaRepository vr;
    @Autowired
    private doacoesRepository dcr;
    @Autowired
    private doadorRepository dor;
    @Autowired
    private ongRepository or;
    private Forecast previsao = new Forecast();
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @GetMapping
    public ResponseEntity montarDados(){
        List<Object[]> vetorObj= new ArrayList();
        for (Vakinha vakinha: vr.findByFkOng(1)){
            Object[] listaObj = new Object[10];
            listaObj[0] = vakinha.getNomeVakinha();
            listaObj[1] = vakinha.getDescVakinha();
            listaObj[2] = vakinha.getDataCriacao().format(formato);
            listaObj[3] = vakinha.getValorNecessario();
            listaObj[4] = dcr.countByFkVakinha(vakinha.getIdVakinha());
            List<Doacao> listaDoacao =  dcr.findByFkVakinhaOrderByDataDoacaoDesc(vakinha.getIdVakinha());
            Double valorAtual = 0.0;
            for (Doacao doacoes : listaDoacao){
                valorAtual+=doacoes.getValorDoacao();
            }
            listaObj[5] = valorAtual;
            listaObj[6] = listaDoacao.get(0).getDataDoacao().format(formato);
            listaObj[7] = listaDoacao.get(0).getValorDoacao();
            listaObj[8] = dor.findByIdDoador(listaDoacao.get(0).getFkDoador()).get(0).getNomeDoador();
            listaObj[9] = previsao.gerarForecast(listaDoacao, vakinha.getDataCriacao().toLocalDate(), vakinha.getValorNecessario(), valorAtual).format(formato);
            vetorObj.add(listaObj);
        }
        gravaArquivoCsv(vetorObj, "Relatorio_"+or.findByIdOng(1).get(0).getNomeOng()+"_"+ LocalDate.now());
        return ResponseEntity.status(200).body(vetorObj);
    }

    public void gravaArquivoCsv(List<Object[]> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;
        nomeArq += ".csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo!");
            System.exit(1);
        }

        try {
            for (Object[] objeto : lista) {
                saida.format("%s;%s;%s;%.2f;%d;%.2f;%s;%.2f;%s;%s\n",objeto[0],objeto[1],objeto[2],
                        Double.parseDouble(objeto[3].toString()),objeto[4],
                        Double.parseDouble(objeto[5].toString()),objeto[6],Double.parseDouble(objeto[7].toString())
                        ,objeto[8],objeto[9]);
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
    }

}
