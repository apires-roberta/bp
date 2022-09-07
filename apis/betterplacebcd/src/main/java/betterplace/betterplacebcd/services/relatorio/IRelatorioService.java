package betterplace.betterplacebcd.services.relatorio;

public interface IRelatorioService {
    String[] montarDados(Integer idOng);

    String[] exportarDados(Integer idOng);
}
