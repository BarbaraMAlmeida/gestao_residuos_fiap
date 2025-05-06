package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;
import model.ErrorMessageModel;
import org.junit.Assert;
import services.CadastroEmergenciaService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CadastroEmergenciaSteps {
    CadastroEmergenciaService cadastroEmergenciaService = new CadastroEmergenciaService();

    @Before
    public void setup() {
        cadastroEmergenciaService.autenticarEObterToken();
    }

    @Dado("que eu tenha os seguintes dados da emergencia:")
    public void queEuTenhaOsSeguintesDadosDaEmergencia(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroEmergenciaService.setFieldsEmergencia(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de emergencias")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeEmergencia(String endPoint) {
        cadastroEmergenciaService.createEmergencia(endPoint);
    }


    @E("o corpo da reposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDaRepostaDeErroDaApiDeveRetornarAMensagem(String message) {
        ErrorMessageModel errorMessageModel = cadastroEmergenciaService.gson.fromJson(
                cadastroEmergenciaService.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessageModel.getErro());
    }

    @E("que o arquivo de contrato de emergencia esperado é o {string}")
    public void queOArquivoDeContratoDeEmergenciaEsperadoÉO(String contract) throws IOException {
        cadastroEmergenciaService.setContract(contract);
    }

    @Então("a resposta da requisição de emergencia deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeEmergenciaDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cadastroEmergenciaService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @Entao("o status code da resposta de emergencia deve ser {int}")
    public void oStatusCodeDaRespostaDeEmergenciaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroEmergenciaService.response.statusCode());
    }
}
