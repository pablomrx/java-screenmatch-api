package br.com.alura.screenmatch.excecao;

public class ErroConversaoDeAnoException extends RuntimeException {
    private String mensagem;

    public ErroConversaoDeAnoException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}