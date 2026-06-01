package br.com.alura.screenmatch.modelos;

import br.com.alura.screenmatch.calculos.Classificavel;

public class Filme extends Titulo implements Classificavel {
    private String diretor;

    public Filme(String nome, int anoDeLancamento, boolean incluidoNoPlano, int duracaoEmMinutos) {
        super(nome, anoDeLancamento, incluidoNoPlano);
        this.setDuracaoEmMinutos(duracaoEmMinutos);
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    @Override
    public String getTipo() {
        return "do filme";
    }

    @Override
    public int getClassificacao() {
        return (int) (obterMedia() / 2);
    }

    @Override
    public String toString() {
        return "Filme: " + this.getNome() + " (" + this.getAnoDeLancamento() + ")";
    }
}