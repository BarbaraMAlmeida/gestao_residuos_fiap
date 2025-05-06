package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.AgendamentoModel;
import model.CaminhaoModel;
import org.junit.Assert;
import services.AgendamentoService;
import services.CaminhaoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CaminhaoSteps {

    CaminhaoService caminhaoService = new CaminhaoService();

    @Before
    public void setup() {
        caminhaoService.autenticarEObterToken();
    }

    @Dado("que eu tenha os seguintes dados para caminhao:")
    public void queEuTenhaOsSeguintesDadosParaCaminhao(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            caminhaoService.setFieldsCaminhao(columns.get("campo"),  columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de caminhoes")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeCaminhoes(String endpoint) {
        caminhaoService.createCaminhao(endpoint);
    }

    @Então("o status code da resposta de caminhao deve ser {int}")
    public void oStatusCodeDaRespostaDeCaminhaoDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, caminhaoService.response.statusCode());
    }

    @E("que o arquivo de contrato de caminhao esperado é o {string}")
    public void queOArquivoDeContratoDeCaminhaoEsperadoÉO(String contract) throws IOException {
        caminhaoService.setContract(contract);
    }

    @Então("a resposta da requisição de caminhao deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeCaminhaoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = caminhaoService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @E("o corpo da resposta de caminhao deve conter a mensagem {string}")
    public void oCorpoDaRespostaDeCaminhaoDeveConterAMensagem(String message) {
        String erro = caminhaoService.response.jsonPath().getString("placa");
        Assert.assertEquals(message, erro);
    }

    @E("a resposta deve conter uma lista de caminhaos")
    public void aRespostaDeveConterUmaListaDeCaminhaos() {
        CaminhaoModel[] caminhoes = caminhaoService.response.getBody().as(CaminhaoModel[].class);
        Assert.assertTrue("A lista de caminhoes deve conter pelo menos um item", caminhoes.length >= 0);
    }

    @Quando("eu enviar uma requisição GET para o endpoint de caminhao {string}")
    public void euEnviarUmaRequisiçãoGETParaOEndpointDeCaminhao(String endpoint) {
        caminhaoService.listCaminhao(endpoint);
    }
}
