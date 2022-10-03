package betterplace.betterplacebcd.classes.forecast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExportarDados {
    public String gravaArquivoTxt(List<Dados> dados, String nomeOng) {
        int contaRegCorpo = 0;
        String header = String.format("0Doação%-45.45s%-19.19s1\n",nomeOng, LocalDateTime.now().format
                (DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        String corpo="";
        for (Dados dado : dados) {
            corpo += "2";
            corpo += String.format("%-30.30s", dado.getNomeCampanha());
            corpo += String.format("%-45.45s", dado.getItemCampanha());
            corpo += String.format("%-100.100s", dado.getDescCampanha());
            corpo += String.format("%-10.10s", dado.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            corpo += String.format("%08.2f", dado.getValorNecessario());
            corpo += String.format("%03d", dado.getQtdDoacoes());
            corpo += String.format("%08.2f", dado.getValorAtual());
            corpo += String.format("%-10.10s", dado.getDataDoacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            corpo += String.format("%08.2f", dado.getValorDoacao());
            corpo += String.format("%-45.45s", dado.getNomeDoador());
            corpo += String.format("%-10.10s", dado.getDataPrevisao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            corpo += String.format("%08.1f\n", dado.getMedia());
            contaRegCorpo++;
        }

        String trailer = String.format("1%010d", contaRegCorpo);
        return header+corpo+trailer;
    }
}