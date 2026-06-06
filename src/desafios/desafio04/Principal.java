package desafios.desafio04;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Principal {
    public static void main(String[] args) {
        Veiculo carro = new Veiculo();
        carro.setMarca("Toyota");
        carro.setModelo("Corolla");
        carro.setAnoDeFabricacao(2022);
        carro.setPreco(75000.00);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String jsonVeiculo = gson.toJson(carro);

        System.out.println("Objeto Veiculo serializado para JSON: ");
        System.out.println(jsonVeiculo);
    }
}