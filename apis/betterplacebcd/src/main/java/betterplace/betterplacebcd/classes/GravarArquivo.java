package betterplace.betterplacebcd.classes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class GravarArquivo {
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
