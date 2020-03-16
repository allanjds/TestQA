package cucumberfcamara;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StepDefinition {

    private static String CEP = "01001000";
    private static String CEP_INVALIDO = "12345678";

    public int status = 0;
    public String ibge = "";

    public StringBuilder request = new StringBuilder();

    // Sucesso
    @Given("Tenho um CEP valido")
    public void tenhoUmCEPValido() throws IOException {
        URL url = new URL("https://viacep.com.br/ws/" + CEP + "/json");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        status = con.getResponseCode();

        String inputLine;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        while ((inputLine = bufferedReader.readLine()) != null) {
            request.append(inputLine);
        }
        bufferedReader.close();

        con.disconnect();
    }

    @Then("Exibir status sucesso")
    public void exibirStatusSucesso() {
        System.out.println("Status da Requisicao: " + status);
    }

    @And("Exiba o numero do IBGE refente a este local")
    public void exibaONumeroDoIBGERefenteAEsteLocal() {
        String[] req = request.toString().split(",");
        ibge = req[7];

        System.out.println(ibge);
    }

    // Invalido
    @Given("Tenho um CEP invalido")
    public void tenhoUmCEPInvalido() throws IOException {
        URL url = new URL("https://viacep.com.br/ws/" + CEP_INVALIDO + "/json");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        status = con.getResponseCode();

        String inputLine;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        while ((inputLine = bufferedReader.readLine()) != null) {
            request.append(inputLine);
        }
        bufferedReader.close();

        con.disconnect();
    }

    @Then("Exibir mensagem CEP INVALIDO")
    public void exibirMensagemCEPINVALIDO() {
        System.out.println(request);
        System.out.println("CEP Inválido");
    }
}
