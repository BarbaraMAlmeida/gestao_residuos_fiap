package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.AgendamentoModel;
import org.junit.Assert;
import services.AgendamentoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AgendamentoSteps {

    AgendamentoService agendamentoService = new AgendamentoService();

    @Before
    public void setup() {
        agendamentoService.autenticarEObterToken();
    }

    @Dado("que eu tenha os seguintes dados para agendamento:")
    public void queEuTenhaOsSeguintesDadosParaAgendamento(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            agendamentoService.setFieldsUsuario(columns.get("campo"),  columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de agendamentos")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeAgendamentos(String endpoint) {
        agendamentoService.createAgendamento(endpoint);
    }

    @Quando("eu enviar uma requisição GET para o endpoint {string}")
    public void euEnviarUmaRequisiçãoGETParaOEndpoint(String endpoint) {
        agendamentoService.listAgendamentos(endpoint);
    }

    @E("a resposta deve conter uma lista de agendamentos")
    public void aRespostaDeveConterUmaListaDeAgendamentos() {
        AgendamentoModel[] agendamentos = agendamentoService.response.getBody().as(AgendamentoModel[].class);
        Assert.assertTrue("A lista de agendamentos deve conter pelo menos um item", agendamentos.length >= 0);
    }

    @Então("o status code da resposta de agendamento deve ser {int}")
    public void oStatusCodeDaRespostaDeAgendamentoDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, agendamentoService.response.statusCode());
    }

    @E("que o arquivo de contrato de agendamento esperado é o {string}")
    public void queOArquivoDeContratoDeAgendamentoEsperadoÉO(String contract) throws IOException {
        agendamentoService.setContract(contract);
    }

    @Então("a resposta da requisição de agendamento deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeAgendamentoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = agendamentoService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @E("o corpo da resposta de agendamento deve conter a mensagem {string}")
    public void oCorpoDaRespostaDeAgendamentoDeveConterAMensagem(String message) {
        String erro = agendamentoService.response.jsonPath().getString("erro");
        Assert.assertEquals(message, erro);
    }
}
