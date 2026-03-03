import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; //Para deixar a data bonita (Ex: 28/02/2026)
import java.util.UUID; //Nova ferramenta para o protocolo único
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;

public class ProcessoRemocao {
    //Atributos Privados (Os fios internos que só você mexe)
    private String urlConteudo;
    private String status; //Ex: "Em análise", "Solicitado", "Removido"
    private String dataSolicitacao;//Onde devemos guardar o registro
    private String protocolo; //Atributo para ID único

    public void salvarEmArquivo(){
        try {
            //1. Cria a pasta se ela não existir
            File pasta = new File("Relatorios_O_Escudo");
            if (!pasta.exists()){
                pasta.mkdir();
            }

            //2. Define o nome do arquivo usando o protocolo
            String nomeArquivo = "Relatorios_O_Escudo/Relatorio_" +this.protocolo + ".txt";

            FileWriter arquivo = new FileWriter(nomeArquivo);
            PrintWriter gravar = new PrintWriter(arquivo);

            //3. Escreve o conteúdo (igual ao seu método gerarRelatorio)
            gravar.println("===RELATÓRIO O ESCUDO===");
            gravar.println("PROTOCOLO: " + this.protocolo);
            gravar.println("CONTEÚDO: " + (this.status.equalsIgnoreCase("Removido") ? "[CONTEÚDO PROTEGIDO]" : this.urlConteudo));
            gravar.println("STATUS: " + this.status.toUpperCase());
            gravar.println("DATA/HORA: " + this.dataSolicitacao);
            gravar.println("=========================");

            arquivo.close(); //fecha o arquivo (importante)
            System.out.println("Arquivo " + nomeArquivo + " gerado com sucesso");
        } catch (IOException e){
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());

        }
    }

    public String getDataSolicitacao() {
        return this.dataSolicitacao;
    }

    //Construtor (A Certidão de nascimento do processo)
    public ProcessoRemocao(String url) {
        this.urlConteudo = url;
        this.status = "Iniciado"; //todo processo nasce como 'Iniciado'
        //o protoclo nas como "Não Gerado"
        this.protocolo = "AGUARDANDO AÇÃO";
    }
    //MODO AUTOMÁTICO: o próprio método "seta" a data
    public void solicitarExclusao(){
        this.status = "Solicitado ao Google";

    //---A MÁGICA DO PROTOCOLO---
    //Gera um código aleatório e pegamos os 8 primeiros caracteres
    this.protocolo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

    //Pega o momento exato do clique
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    //grava no atributo privado
    this.dataSolicitacao = agora.format(formatador);
        System.out.println("Solicitação enviada! Protocolo: " + this.protocolo);
        System.out.println("Botão APERTADO: Pedido enviado para URL: " + this.urlConteudo);
       }

    public String verificarStatus(){
        return this.status;
    }

    public void gerarRelatorio(){
        System.out.println("\n------------------------------------");
        System.out.println("    SISTEMA O ESCUDO - RELATÓRIO      ");
        System.out.println("--------------------------------------");
        System.out.println("PROTOCOLO: " + this.protocolo);//Novo campo!

        //O PULO DO GATO COM TERNÁRIO:
        //Se o status for "Removido", esconde a URL, Se não mostra.
        String exibicaoUrl = (this.status.equalsIgnoreCase("Removido") ? "[CONTEÚDO PROTEGIDO]" : this.urlConteudo);
        System.out.println("CONTEÚDO : " + exibicaoUrl);

        System.out.println("STATUS: " + this.status.toUpperCase());
        System.out.println("DATA/HORA: " + (this.dataSolicitacao != null ? this.dataSolicitacao : "---"));
        System.out.println("======================================");

        if (this.dataSolicitacao != null){
            System.out.println("REGISTRO: " + this.dataSolicitacao);
        }else{
            System.out.println("REGISTRO: aguardando solicitação...");
        }
        System.out.println("---------------------------------------");
    }
    public void finalizarRemocao(){
        this.status = "Removido";
        System.out.println("SUCESSO: O conteúdo foi removido da rede!");
    }
}
