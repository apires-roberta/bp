package betterplace.betterplacebcd.classes;

import javax.swing.text.DateFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Importacao {
    public void leArquivoTxt(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome = "",
                dataDoacoes = "",
                nomeDoador = "",
                emailDoador = "",
                numeroDoador = "",
                cpf = "",
                horarioDoacao = "",
                nomeCampanha = "";
        Double valorDoado = 0.0;
        int contaRegCorpoLido = 0;
        int qtdRegCorpoGravado;

        List<DadosUsuario> listaLida = new ArrayList<>();
        List<DadosDoacoes> listaLida2 = new ArrayList<>();

        // try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro);
        }

        // try-catch para ler e fechar o arquivo
        try {
            // Leitura do primeiro registro do arquivo
            registro = entrada.readLine();

            while (registro != null) {
                tipoRegistro = registro.substring(0, 1);
                if (tipoRegistro.equals("0")) {
                    //Precisa pegar o id que está no banco pelo nome da ong
                    dataDoacoes = registro.substring(52, 62);
                } else if (tipoRegistro.equals("1")) {
                    System.out.println("É um registro de trailer");
                    nomeDoador = registro.substring(1, 46).trim();
                    emailDoador = registro.substring(46, 56).trim();
                    numeroDoador = registro.substring(56, 67);
                    cpf = registro.substring(67, 78);

                    DadosUsuario a = new DadosUsuario(nomeDoador, emailDoador, numeroDoador, cpf);

                    listaLida.add(a);
                } else if (tipoRegistro.equals("2")) {
                    System.out.println("É um registro de corpo");
                    valorDoado = Double.valueOf(registro.substring(1, 7));
                    horarioDoacao = registro.substring(7, 15);
                    nomeCampanha = registro.substring(15, 50).trim();
                    contaRegCorpoLido++;

                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

                    LocalDateTime data = LocalDateTime.parse(dataDoacoes + " " + horarioDoacao, formato);

                    DadosDoacoes a = new DadosDoacoes(valorDoado, data, nomeCampanha);

                    listaLida2.add(a);

                } else if (tipoRegistro.equals("3")) {
                    qtdRegCorpoGravado = Integer.parseInt(registro.substring(1, 6));
                    if (contaRegCorpoLido == qtdRegCorpoGravado) {
                        System.out.println("Quantidade de registros lidos é compatível " +
                                "com a quantidade de registros gravados");

                        for (DadosUsuario a : listaLida) {
                            System.out.println(a);
                        }

                        for (DadosDoacoes a : listaLida2) {
                            System.out.println(a);
                        }
                    } else {
                        System.out.println("Quantidade de registros lidos não é compatível " +
                                "com a quantidade de registros gravados");
                    }

                } else {
                    System.out.println("Tipo de registro inválido!");
                }

                // Lê o próximo registro
                registro = entrada.readLine();
            }

            entrada.close();
        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo: " + erro);
        }
    }
}
