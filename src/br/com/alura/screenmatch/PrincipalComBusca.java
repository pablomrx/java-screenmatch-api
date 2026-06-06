package br.com.alura.screenmatch;

import br.com.alura.screenmatch.excecao.ErroConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOMDB;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);

        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (true) {

            System.out.println("\nDigite um filme para busca: ");
            var busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }

            String apiKey = System.getenv("OMDB_API_KEY");

            String endereco = "https://omdbapi.com/?t="
                    + URLEncoder.encode(busca, StandardCharsets.UTF_8)
                    + "&apikey=" + apiKey;

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);

                TituloOMDB meuTituloOMDB = gson.fromJson(json, TituloOMDB.class);
                System.out.println(meuTituloOMDB);

                Titulo meuTitulo = new Titulo(meuTituloOMDB);
                System.out.println("\nTitulo convertido: ");
                System.out.println(meuTitulo);

                FileWriter escrita = new FileWriter("filmes.txt");
                escrita.write(meuTitulo.toString());
                escrita.close();

                titulos.add(meuTitulo);
            } catch (NumberFormatException e) {
                System.out.println("Ocorreu um erro: ");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Ocorreu algum erro de argumento na busca, verifique o endereço.");
                System.out.println(e.getMessage());
            } catch (ErroConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(titulos);

        try (FileWriter escrita = new FileWriter("filmes.json")) {
            escrita.write(gson.toJson(titulos));
        }

        System.out.println("\nO programa finalizou corretamente!");
    }
}