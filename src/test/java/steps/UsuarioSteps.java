package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import org.json.JSONException;
import org.junit.Assert;
import services.UsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UsuarioSteps {


    UsuarioService usuarioService = new UsuarioService();


    @Dado("que eu tenha os seguintes dados para cadastro:")
    public void queEuTenhaOsSeguintesDadosParaCadastro(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            usuarioService.setFieldsUsuario(columns.get("campo"),  columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de usuarios")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeUsuarios(String endpoint) {

        usuarioService.createUsuario(endpoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, usuarioService.response.statusCode());
    }


    @E("o corpo da resposta deve conter a mensagem {string}")
    public void oCorpoDaRespostaDeveConterAMensagem(String message) {
        String erro = usuarioService.response.jsonPath().getString("erro");
        Assert.assertEquals(message, erro);
    }

    @Dado("que eu tenha os seguintes dados para login:")
    public void queEuTenhaOsSeguintesDadosParaLogin(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            usuarioService.setFieldsLoginUsuario(columns.get("campo"),  columns.get("valor"));
        }
    }


    @Quando("eu enviar a requisição para o endpoint {string} de login de usuarios")
    public void euEnviarARequisiçãoParaOEndpointDeLoginDeUsuarios(String endpoint) {
        usuarioService.loginUsuario(endpoint);
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract)  throws IOException {
        usuarioService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException, JSONException {
        Set<ValidationMessage> validateResponse = usuarioService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
