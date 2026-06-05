package desafios.desafio03;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

record UsuarioGitHub(
        String login,
        String name,
        String html_url,
        String avatar_url,
        int public_repos,
        int followers) {
}

public class ConsultaGitHub {

    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite o nome de usuário do GitHub para consultar informações: ");
        String username = leitura.nextLine();

        String endereco = "https://api.github.com/users/" + username;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .header("Accept", "application/vnd.github.v3+json")
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 404) {
                throw new ErroConsultaGitHubException("Usuário não encontrado no GitHub.");
            }

            if (response.statusCode() != 200) {
                throw new RuntimeException(
                        "Erro na consulta. Código HTTP: " + response.statusCode());
            }

            String json = response.body();
            Gson gson = new Gson();

            UsuarioGitHub usuario =
                    gson.fromJson(json, UsuarioGitHub.class);

            System.out.println("""
                    Login: %s
                    Nome: %s
                    Perfil: %s
                    Foto: %s
                    Repositórios: %d
                    Seguidores: %d
                    """
                    .formatted(
                            usuario.login(),
                            usuario.name(),
                            usuario.html_url(),
                            usuario.avatar_url(),
                            usuario.public_repos(),
                            usuario.followers()));

        } catch (IOException | InterruptedException e) {
            System.out.println("Opss… Houve um erro durante a consulta à API do GitHub.");
            e.printStackTrace();
        } catch (ErroConsultaGitHubException e) {
            System.out.println(e.getMessage());
        }

        leitura.close();
    }
}