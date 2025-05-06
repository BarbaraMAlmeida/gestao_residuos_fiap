package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.Before;
import io.cucumber.java.pt.*;
import model.RotaModel;
import org.junit.Assert;
import services.RotaService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RotaSteps {

    RotaService rotaService = new RotaService();

    @Before
    public void setup() {
        rotaService.autenticarEObterToken();
    }

    @Dado("que eu tenha os seguintes dados da rota:")
    public void queEuTenhaOsSeguintesDadosDaRota(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            rotaService.setFieldsRotas(columns.get("campo"),  columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de rotas")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeRotas(String endpoint) {
        rotaService.createRota(endpoint);
    }

    @Então("o status code da resposta de rotas deve ser {int}")
    public void oStatusCodeDaRespostaDeRotasDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, rotaService.response.statusCode());
    }

    @E("que o arquivo de contrato de rota esperado é o {string}")
    public void queOArquivoDeContratoDeRotaEsperadoÉO(String contract) throws IOException {
        rotaService.setContract(contract);
    }

    @Então("a resposta da requisição da rota deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDaRotaDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = rotaService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }


    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção de rotas")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDeRotas(String endPoint) {
        rotaService.deleteRota(endPoint);
    }

    @Dado("que eu recupere o ID da rota criada no contexto")
    public void queEuRecupereOIDDaRotaCriadaNoContexto() {
        rotaService.retrieveRotaId();
    }

    @Entao("o status code da resposta de rota deve ser {int}")
    public void oStatusCodeDaRespostaDeRotaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, rotaService.response.statusCode());
    }
}
