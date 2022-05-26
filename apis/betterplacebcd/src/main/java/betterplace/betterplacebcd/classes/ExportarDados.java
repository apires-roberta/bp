package betterplace.betterplacebcd.classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExportarDados {
    public String gravaArquivoTxt(List<Dados> dados, String nomeOng) {
        int contaRegCorpo = 0;
        String header = String.format("0Doação%-45.45s%-19.19s1\n",nomeOng, LocalDateTime.now().format
                (DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        String corpo="";
        for (Dados d : dados) {
            corpo += "2";
            corpo += String.format("%-30.30s", d.getNomeCampanha());
            corpo += String.format("%-45.45s", d.getItemCampanha());
            corpo += String.format("%-100.100s", d.getDescCampanha());
            corpo += String.format("%-10.10s", d.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            corpo += String.format("%08.2f", d.getValorNecessario());
            corpo += String.format("%03d", d.getQtdDoacoes());
            corpo += String.format("%08.2f", d.getValorAtual());
            corpo += String.format("%-10.10s", d.getDataDoacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            corpo += String.format("%08.2f", d.getValorDoacao());
            corpo += String.format("%-45.45s", d.getNomeDoador());
            corpo += String.format("%-10.10s", d.getDataPrevisao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            corpo += String.format("%08.1f\n", d.getMedia());
            contaRegCorpo++;
        }

        String trailer = String.format("1%010d", contaRegCorpo);
        return header+corpo+trailer;
    }
}
