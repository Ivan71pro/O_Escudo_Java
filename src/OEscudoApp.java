import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.ArrayList; //A ferramenta para cria listas
import java.io.FileWriter;
import java.io.PrintWriter;

public class OEscudoApp {
    public static void main(String[] args) {

        //--DAQUI PARA BAIXO É O CÓDIGO NOVO (RUMO AO EXCEL)---
        ArrayList<Solicitacao> listaAtualizada = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        String continuar = "s";


        System.out.println("=== SISTEMA O ESCUDO: CADASTRO DE REMOÇÃO ===");

        while (continuar.equalsIgnoreCase("s")){
            System.out.println("Nome do Cliente: ");
            String nome = teclado.nextLine();

            //LINK COM VALIDAÇÃO (O bloco novo entra aqui!)
            String link = "";
            boolean linkValido = false;
            while (!linkValido){
                System.out.println("Link para remover: ");
                link = teclado.nextLine();

                if (link.toLowerCase().startsWith("http")){
                    linkValido = true;
                } else {
                    System.out.println(">>>ERRO: O link deve começar com 'http'. Tente novamente.");
                }
            }

            System.out.println("Data (dd/mm/aaaa)");
            String data = "";
            boolean datavalida = false;
            while (!datavalida){
                System.out.println("Data (dd/mm/aaaa - exatamente 10 caracteres): ");
                data = teclado.nextLine();

                if (data.length() == 10){
                    datavalida = true;
                } else {
                    System.out.println(">>>ERRO: Formato inválido! Use o padrão 00/00/0000.");
                }
            }

            //Adicionamos na list o que acabou de digitar!
            listaAtualizada.add(new Solicitacao(nome,link,data));

            System.out.println("Deseja cadastrar outro? (s/n): ");
            continuar = teclado.nextLine();
            System.out.println("-------------------------------------");
        }

        //Agora cada entrada tem "nome", "Link" e "data"

        /*listaAtualizada.add(new Solicitacao("João Silva", "http://maps.google.com/exemplo", "02/03/2026"));
        listaAtualizada.add(new Solicitacao("Maria Oliveira", "http://maps.google.com/exemplo2", "02/03/2026"));*/

        System.out.println("=== O ESCUDO PREPARANDO DADOS PARA EXCEL ===\n");

        //3. O "for" percorre a listaAtualizada que acabamos de criar
        for (Solicitacao s : listaAtualizada){
            s.exibirResumo();
        }

        System.out.println("\nTotal de registros prontos para exportar: " + listaAtualizada.size());

        //EXPORTANDO PARA EXCEL (CSV)
        try {
            //Criamos um objeto que representa a pasta
            java.io.File pasta = new java.io.File("Relatórios");

            //Se a pasta NÃO existi, o jaca cria ela agora
            if (!pasta.exists()){
                pasta.mkdirs();
                System.out.println(">> Pasta 'Relatórios' criada com sucesso!");
            }

            //Pegamos a data e hora (você já fez isso!)

            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd_MM_yyyy_HHmm");
            String dataFormatada = agora.format(formato);

            //O segredo: colocamos "Relatórios/" na frente do nome
            String nomeArquivo = "Relatórios/O_Escudo_" + dataFormatada + ".csv";

            FileWriter arquivoExcel = new FileWriter(nomeArquivo);
            PrintWriter gravar = new PrintWriter(arquivoExcel);

           gravar.println("Cliente; Link de Origem; status; Data");
           for (Solicitacao s : listaAtualizada){
               gravar.println(s.getCliente() + ";" + s.getUrlAlvo() + ";" + s.getStatus() + ";" + s.getDataSolicitacao());
           }
           gravar.close();
            System.out.println("\n>>> SUCESSO: Planilha gerada!");
        }
        //O 'catch' diz: "...mas se der erro (IOException), faça isso aqui:"
        catch (IOException e){
            System.out.println("Erro ao gravar o arquivo:" + e.getMessage());
        }

        //1. Criamos a "Pasta de processo" (A Lista)
       /* ArrayList<ProcessoRemocao> listaDeProtecao = new ArrayList<>();

        //2. adicionamos vários casos de uma vez
        listaDeProtecao.add(new ProcessoRemocao("https://facebook.com/ofensa01"));
        listaDeProtecao.add(new ProcessoRemocao("https://instagram.com/fake02"));
        listaDeProtecao.add(new ProcessoRemocao("https://twitter.com/post03"));

        //O CONTADOR: Começa em zero
        int totalRemovidos = 0;

        try {
            //1. Criamos o aquivo de fechamento fora do loop
            FileWriter arqFechamento = new FileWriter("Relatorios_O_Escudo/FECHAMENTO_DO_DIA.txt");
            PrintWriter gravarGeral = new PrintWriter(arqFechamento);

            gravarGeral.println("=======FECHAMENTO DIÁRIO O ESCUDO======");
            gravarGeral.println("Data: 01/03/2026");
            gravarGeral.println("---------------------------------------");

            for (ProcessoRemocao processo : listaDeProtecao) {
                processo.solicitarExclusao();
                processo.finalizarRemocao();
                processo.salvarEmArquivo();

                totalRemovidos ++;

                //2. Gravamos uma lista para cada processo no relatório geral
                gravarGeral.println("Protocolo: " + processo.verificarStatus() + " | Sucesso no link: " + totalRemovidos);

        }
            //3. O Grande final: Gravamos o total no fim do arquivo único
            gravarGeral.println("-------------------------------------------------");
            gravarGeral.println("TOTAL DE LINKS PROTEGIDOS HOJE: " + totalRemovidos);
            gravarGeral.println("=================================================");

            arqFechamento.close(); //Fecha o "Super Relatório"
            System.out.println("\n>>> SUPER RALTÓRIO 'FECHAMENTO_DO_DIA.txt' GERADO!");

        } catch (IOException e) {
            System.out.println("Erro ao gerar fechamento: " +e.getMessage());
        }

        System.out.println("Resumo do console: " + totalRemovidos + " protegidos.");
        /*EXIBINDO O RESULTADO FINAL
        System.out.println("=======================");
        System.out.println(" RESUMO DO DIA: " + "Links protegidos.");
        System.out.println("=======================");

        System.out.println("---" + listaDeProtecao.size() + "FIM DO PROCESSAMENTO EM MASSA ---");

       ProcessoRemocao p1 = new ProcessoRemocao("https://facebook.com/foto123");

        //Relatório inicial (ainda sem data)
        p1.gerarRelatorio();

        //Executando a ação
        p1.solicitarExclusao();

        //AQUI ESTÁ O SEGREDO:
        p1.finalizarRemocao();//Agora o status vira "Removido"

        //Relatório final (Com tudo preenchido automaticamente)
        p1.gerarRelatorio();//Aqui vai mostrar a URL

        //Criando um novo caso para O Escudo
        ProcessoRemocao caso01 = new ProcessoRemocao("www.link-ofensivo.com/foto");

        System.out.println("Status atual: " + caso01.verificarStatus());

        //Apertando o botão de ação
        caso01.solicitarExclusao();

        //Se quiser que este também proteja a URL:
        caso01.finalizarRemocao();
        caso01.gerarRelatorio();

        System.out.println("Novo status: " + caso01.verificarStatus());*/
    }
}
