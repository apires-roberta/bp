package betterplace.betterplacebcd.services.relatorio;

import betterplace.betterplacebcd.classes.ed.ListaObj;
import betterplace.betterplacebcd.classes.forecast.*;
import betterplace.betterplacebcd.data.dto.usuario.ReadUsuarioDto;
import betterplace.betterplacebcd.entidade.Campanha;
import betterplace.betterplacebcd.entidade.Doacao;
import betterplace.betterplacebcd.repositorio.CampanhaRepository;
import betterplace.betterplacebcd.repositorio.DoacoesRepository;
import betterplace.betterplacebcd.servicesreferences.IDoadorService;
import betterplace.betterplacebcd.servicesreferences.IOngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;


@Service
public class RelatorioService implements IRelatorioService {
    @Autowired
    private CampanhaRepository campanhaRepository;
    @Autowired
    private DoacoesRepository doacoesRepository;
    @Autowired
    private IDoadorService _doadorService;
    @Autowired
    private IOngService _ongService;
    private Forecast previsao = new Forecast();
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private GravarArquivo gravarArquivo = new GravarArquivo();
    private ExportarDados exportarDados = new ExportarDados();

    @Override
    public String[] montarDados(Integer idOng) {
        if (campanhaRepository.findByOngCod(idOng).isEmpty()) {
            return null;
        }
        ListaObj<DadosCsv> dados = new ListaObj(campanhaRepository.countByOngCod(idOng));
        for (Campanha campanha : campanhaRepository.findByOngCod(idOng)) {
            Object[] listaObj = new Object[12];
            listaObj[0] = campanha.getNomeCampanha();
            listaObj[1] = campanha.getNomeItem();
            listaObj[2] = campanha.getDescCampanha();
            listaObj[3] = campanha.getDataCriacao().format(formato);
            listaObj[4] = campanha.getValorNecessario();
            Integer qtdDoacoes = doacoesRepository.countByCampanhaIdCampanha(campanha.getIdCampanha());
            listaObj[5] = qtdDoacoes == null ? 0 : qtdDoacoes;

            List<Doacao> listaDoacao = doacoesRepository.findByCampanhaIdCampanhaOrderByDataDoacaoDesc(campanha.getIdCampanha());
            Double valorAtual = 0.0;
            if (!listaDoacao.isEmpty()) {
                valorAtual = somaDoacoesRecursivo(listaDoacao, 0);
                listaObj[6] = valorAtual;
                listaObj[7] = listaDoacao.get(0).getDataDoacao().format(formato);
                listaObj[8] = listaDoacao.get(0).getValorDoacao();
                ReadUsuarioDto doador = _doadorService.getUsuarioById(listaDoacao.get(0).getDoador().getCod());
                if (doador == null) {
                    listaObj[9] = "Usuario nao esta mais no site";
                } else {
                    listaObj[9] = doador.getNome();
                }
                Media media = new Media(campanha.getDataCriacao().toLocalDate());
                listaObj[10] = media.calcularMedia(valorAtual);
                LocalDate dia = previsao.gerarForecast(listaDoacao, campanha.getDataCriacao().toLocalDate(), campanha.getValorNecessario(), valorAtual);
                System.out.println(dia);
                if (dia != null) {
                    listaObj[11] = dia.format(formato);
                } else {
                    listaObj[11] = media.previsaoMedia(valorAtual, campanha.getValorNecessario()).format(formato);
                }
            } else {
                listaObj[6] = valorAtual;
                listaObj[7] = "Não houve";
                listaObj[8] = 0.0;
                listaObj[9] = "Não há dados";
                listaObj[10] = 0.0;
                listaObj[11] = "Não há dados";
            }

            dados.adiciona(new DadosCsv(listaObj));
            Integer tamanho = dados.getTamanho();
            for (int i = 0; i < tamanho - 1; i++) {
                Double menor = dados.getElemento(i).getValorNecessario();
                int posicao = 0;
                for (int j = i + 1; j < tamanho; j++) {
                    if (menor > dados.getElemento(j).getValorNecessario()) {
                        posicao = j;
                    }
                }
                if (posicao > 0) {
                    dados.ordenar(i, posicao, dados.getElemento(i));
                }
            }
        }

        String nome = "Relatorio_" + _ongService.getOngById(idOng).getNome() + "_" + LocalDate.now();
        String csv = gravarArquivo.gravaArquivoCsv(dados, nome);
        String[] infos = {nome, csv};

        return infos;
    }

    private Double somaDoacoesRecursivo(List<Doacao> listaDoacao, int posicao) {
        if (posicao >= listaDoacao.size())
            return 0d;

        return listaDoacao.get(posicao).getValorDoacao() + somaDoacoesRecursivo(listaDoacao, posicao+1);
    }

    @Override
    public String[] exportarDados(Integer idOng) {
        String nomeOng;
        String nomeCampanha, itemCampanha, descCampanha, nomeDoador = "";
        Double valorNecessario, valorAtual, valorDoacao = 0.0, media = 0.0;
        int qtdDoacoes;
        LocalDate dataCriacao, dataDoacao = null, dataPrevisao = null;
        List<Dados> dados = new ArrayList();
        ReadUsuarioDto ong = _ongService.getOngById(idOng);
        nomeOng = ong.getNome();

        for (Campanha campanha : campanhaRepository.findByOngCod(idOng)) {
            nomeCampanha = campanha.getNomeCampanha();
            itemCampanha = campanha.getNomeItem();
            descCampanha = campanha.getDescCampanha();
            valorNecessario = campanha.getValorNecessario();
            dataCriacao = campanha.getDataCriacao().toLocalDate();
            qtdDoacoes = doacoesRepository.countByCampanhaIdCampanha(campanha.getIdCampanha());
            List<Doacao> listaDoacao = doacoesRepository.findByCampanhaIdCampanhaOrderByDataDoacaoDesc(campanha.getIdCampanha());
            valorAtual = 0.0;
            if (!listaDoacao.isEmpty()) {
                valorAtual = somaDoacoesRecursivo(listaDoacao, 0);
                dataDoacao = listaDoacao.get(0).getDataDoacao().toLocalDate();
                valorDoacao = listaDoacao.get(0).getValorDoacao();
                ReadUsuarioDto doador = _doadorService.getUsuarioById(listaDoacao.get(0).getDoador().getCod());
                if (doador == null) {
                    nomeDoador = "Usuario nao esta mais no site";
                } else {
                    nomeDoador = doador.getNome();
                }
                Media m = new Media(campanha.getDataCriacao().toLocalDate());
                media = m.calcularMedia(valorAtual);
                LocalDate dia = previsao.gerarForecast(listaDoacao, campanha.getDataCriacao().toLocalDate(), campanha.getValorNecessario(), valorAtual);
                if (dia != null) {
                    dataPrevisao = dia;
                } else {
                    dataPrevisao = m.previsaoMedia(valorAtual, campanha.getValorNecessario());
                }
            }
            dados.add(new Dados(nomeCampanha, itemCampanha, descCampanha, dataCriacao, dataDoacao, nomeDoador, dataPrevisao,
                    valorNecessario, valorAtual, valorDoacao, media, qtdDoacoes));
        }

        String export = exportarDados.gravaArquivoTxt(dados, nomeOng);
        String[] info = {nomeOng, export};

        return info;
    }
}