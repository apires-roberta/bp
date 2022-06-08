package betterplace.betterplacebcd.classes;

import java.time.LocalDateTime;

public class DadosDoacoes {

    private Double valorDoado;
    private LocalDateTime dataDoacao;
    private String nomeCampanha;

    public DadosDoacoes(Double valorDoado, LocalDateTime dataDoacao, String nomeCampanha) {
        this.valorDoado = valorDoado;
        this.dataDoacao = dataDoacao;
        this.nomeCampanha = nomeCampanha;
    }
}
