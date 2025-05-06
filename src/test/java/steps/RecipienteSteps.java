package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;
import model.EmergenciaModel;
import model.ErrorMessageModel;
import model.RecipienteModel;
import org.junit.Assert;
import services.RecipienteService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecipienteSteps {
    RecipienteService recipienteService = new RecipienteService();

    @Before
    public void setup() {
        recipienteService.autenticarEObterToken();
    }

    @Dado("que eu tenha os seguintes dados do recipiente:")
    public void queEuTenhaOsSeguintesDadosDaRecipiente(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            recipienteService.setFieldsRecipiente(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de recipientes")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeRecipiente(String endPoint) {
        recipienteService.createRecipiente(endPoint);
    }


    @E("que o arquivo de contrato de recipiente esperado é o {string}")
    public void queOArquivoDeContratoDeRecipienteEsperadoÉO(String contract) throws IOException {
        recipienteService.setContract(contract);
    }

    @Então("a resposta da requisição de recipiente deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeRecipienteDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = recipienteService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @Entao("o status code da resposta de recipiente deve ser {int}")
    public void oStatusCodeDaRespostaDeRecipienteDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, recipienteService.response.statusCode());
    }

    @E("o corpo da resposta de erro da api de recipiente deve retornar a mensagem {string}")
    public void oCorpoDaRespostaDeErroDaApiDeRecipienteDeveRetornarAMensagem(String message) {
        String erro = recipienteService.response.jsonPath().getString("erro");
        Assert.assertEquals(message, erro);
    }


    @Quando("eu enviar uma requisição GET para o endpoint de recipiente {string}")
    public void euEnviarUmaRequisiçãoGETParaOEndpointDeRecipiente(String endpoint) {
        recipienteService.listRecipientes(endpoint);
    }

    @Então("o status code da resposta de lista de recipiente deve ser {int}")
    public void oStatusCodeDaRespostaDeListaDeRecipienteDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, recipienteService.response.statusCode());
    }

    @E("a resposta deve conter uma lista de recipientes")
    public void aRespostaDeveConterUmaListaDeRecipientes() {
        RecipienteModel[] recipientes = recipienteService.response.getBody().as(RecipienteModel[].class);
        Assert.assertTrue("A lista de recipientes deve conter pelo menos um item", recipientes.length >= 0);
    }
}
