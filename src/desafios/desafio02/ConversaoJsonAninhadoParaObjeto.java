package desafios.desafio02;

import com.google.gson.Gson;

record Editora(String nome, String cidade) {}
record Livro(String titulo, String autor, Editora editora) {}

public class ConversaoJsonAninhadoParaObjeto {

    public static void main(String[] args) {
        String jsonLivro = "{\"titulo\":\"Clean Code\",\"autor\":\"Robert C. Martin\",\"editora\":{\"nome\":\"Prentice Hall\",\"cidade\":\"Boston\"}}";

        Gson gson = new Gson();
        Livro livro = gson.fromJson(jsonLivro, Livro.class);

        System.out.println("Objeto Livro: " + livro);
    }
}