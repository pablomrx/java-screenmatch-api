package br.com.alura.screenmatch;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOMDB;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);

        System.out.println("Digite um filme para busca: ");
        var busca = leitura.nextLine();
        String apiKey = System.getenv("OMDB_API_KEY");

        String endereco = "https://omdbapi.com/?t="
                + URLEncoder.encode(busca, StandardCharsets.UTF_8)
                + "&apikey=" + apiKey;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        TituloOMDB meuTituloOMDB = gson.fromJson(json, TituloOMDB.class);
        System.out.println(meuTituloOMDB);

        Titulo meuTitulo = new Titulo(meuTituloOMDB);
        System.out.println("\nTitulo convertido: ");
        System.out.println(meuTitulo);
    }
}