import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;

public class OEscudoApp {
    public static void main(String[] args) {
        ArrayList<Solicitacao> listaAtualizada = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("\n=== 🛡️ SISTEMA O ESCUDO: MENU PRINCIPAL ===");
            System.out.println("1. Cadastrar nova remoção");
            System.out.println("2. Gerar Relatório Excel (CSV)");
            System.out.println("3. Sair do Sistema");
            System.out.print("Escolha uma opção: ");

            // Corrigido: usamos nextInt para números e nextLine para limpar o buffer
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- NOVO CADASTRO ---");
                    System.out.print("Nome do Cliente: ");
                    String nome = teclado.nextLine();

                    // Validação do Link
                    String link = "";
                    boolean linkValido = false;
                    while (!linkValido) {
                        System.out.print("Link para remover: ");
                        link = teclado.nextLine();
                        if (link.toLowerCase().startsWith("http")) {
                            linkValido = true;
                        } else {
                            System.out.println(">>> ERRO: O link deve começar com 'http'.");
                        }
                    }

                    // Validação da Data
                    String data = "";
                    boolean datavalida = false;
                    while (!datavalida) {
                        System.out.print("Data (dd/mm/aaaa): ");
                        data = teclado.nextLine();
                        if (data.length() == 10) {
                            datavalida = true;
                        } else {
                            System.out.println(">>> ERRO: Formato inválido! Use 00/00/0000.");
                        }
                    }

                    // Adiciona na lista
                    listaAtualizada.add(new Solicitacao(nome, link, data));
                    System.out.println(">>> Registro adicionado com sucesso!");
                    break;

                case 2:
                    if (listaAtualizada.isEmpty()) {
                        System.out.println(">>> ERRO: Não há dados para exportar. Cadastre primeiro!");
                    } else {
                        gerarRelatorio(listaAtualizada);
                    }
                    break;

                case 3:
                    System.out.println("Encerrando o sistema O Escudo. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    // Criamos esse método fora do main para o código não ficar uma bagunça
    public static void gerarRelatorio(ArrayList<Solicitacao> lista) {
        try {
            java.io.File pasta = new java.io.File("Relatórios");
            if (!pasta.exists()) {
                pasta.mkdirs();
            }

            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd_MM_yyyy_HHmm");
            String nomeArquivo = "Relatórios/O_Escudo_" + agora.format(formato) + ".csv";

            FileWriter arquivoExcel = new FileWriter(nomeArquivo);
            PrintWriter gravar = new PrintWriter(arquivoExcel);

            gravar.println("Cliente; Link de Origem; status; Data");
            for (Solicitacao s : lista) {
                gravar.println(s.getCliente() + ";" + s.getUrlAlvo() + ";" + s.getStatus() + ";" + s.getDataSolicitacao());
            }
            gravar.close();
            System.out.println("\n>>> SUCESSO: Planilha '" + nomeArquivo + "' gerada!");
        } catch (IOException e) {
            System.out.println("Erro ao gravar arquivo: " + e.getMessage());
        }
    }
}