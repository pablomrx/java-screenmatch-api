package br.com.alura.screenmatch;

import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;
import br.com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrincipalComListas {
    public static void main(String[] args) {
        // Filme 1
        Filme filme01 = new Filme("Guardiões da Galáxia", 2014, true, 121);
        filme01.avalia(9);

        Filme f1 = filme01; // referência

        // Filme 2
        Filme filme02 = new Filme("O poderoso chefão", 1972, false, 175);
        filme02.avalia(7.8);

        // Filme 3
        Filme filme03 = new Filme("Dogville", 2003, true, 178);
        filme03.avalia(8.3);

        // Serie 1
        Serie serie01 = new Serie("Lost", 2004, true);

        // Lista
        ArrayList<Titulo> lista = new ArrayList<>();

        lista.add(filme01);
        lista.add(filme02);
        lista.add(filme03);
        lista.add(serie01);

        for (Titulo item : lista) {
            System.out.println(item.getNome());
            if (item instanceof Filme filme && filme.getClassificacao() > 2) {
                System.out.println("Classificação: " + filme.getClassificacao() + "\n");
            }
        }

        List<String> buscaPorArtista = new ArrayList<>();
        System.out.println("\n----- Lista de artistas -----");
        buscaPorArtista.add("Adam Sandler");
        buscaPorArtista.add("Daniel Craig");
        buscaPorArtista.add("Chris Pratt");

        System.out.println(buscaPorArtista);

        Collections.sort(buscaPorArtista);
        System.out.println("----- Depois da ordenação -----");
        System.out.println(buscaPorArtista);

        System.out.println("\nOrdenação por ordem alfabética: ");
        Collections.sort(lista);
        System.out.println(lista);

        System.out.println("Ordenação por ano de lançamento: ");
        lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));
        System.out.println(lista);
    }
}