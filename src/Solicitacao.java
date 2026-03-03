public class Solicitacao {
    //Atributos (as colunas da minha planilha)
    private String cliente;
    private String urlAlvo;
    private String status;
    private String dataSolicitacao;

    //Construtor (como criamos uma nova linha na planilha)

    public Solicitacao(String cliente, String urlAlvo, String dataSolicitacao) {
        this.cliente = cliente.trim();//Remove espaços acidentais no inicio/fim
        this.urlAlvo = urlAlvo.trim().toLowerCase();//deixa o link em minúsculo
        this.status = "pendente";
        this.dataSolicitacao = dataSolicitacao;
    }

    //Getters e Setters (Para ler e alterar os dados com segurança)

    public String getStatus() {
        return status;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getUrlAlvo() {
        return urlAlvo;
    }

    public void setUrlAlvo(String urlAlvo) {
        this.urlAlvo = urlAlvo;
    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void exibirResumo(){
        System.out.println("-- O Escudo: Relatório de Remoção --");
        System.out.println("Cliente: " + cliente);
        System.out.println("Link: " + urlAlvo);
        System.out.println("Status Atual: " + status);
        System.out.println("------------------------------------");
    }
}
