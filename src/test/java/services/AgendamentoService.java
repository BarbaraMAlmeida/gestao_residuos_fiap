package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.AgendamentoModel;
import model.UsuarioModel;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class AgendamentoService {

    final AgendamentoModel agendamentoModel = new AgendamentoModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080";

    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();
    String jwtToken;

    public void autenticarEObterToken() {
        String loginBody = """
        {
           "email": "babii@teste.com",
           "senha": "12667763"
        }
    """;

        Response loginResponse = given()
                .contentType(ContentType.JSON)
                .body(loginBody)
                .when()
                .post(baseUrl + "/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        jwtToken = "Bearer " + loginResponse.jsonPath().getString("token");
    }


    public void setFieldsUsuario(String field, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Valor nulo ou vazio para o campo: " + field);
        }

        switch (field.trim()) {
            case "dtAgendamento" ->
                    agendamentoModel.setDtAgendamento(LocalDate.parse(value));

            case "statusAgendamento" ->
                    agendamentoModel.setStatusAgendamento(value);

            case "rota" ->
                    agendamentoModel.setRota(Long.parseLong(value));

            default -> throw new IllegalArgumentException("Campo inesperado: " + field);
        }
    }

    public void createAgendamento(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(agendamentoModel);
        System.out.println("Payload enviado: " + bodyToSend);

        response = given()
                .header("Authorization", jwtToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void listAgendamentos (String endpoint) {
        String url = baseUrl + endpoint;

        response = given()
                .header("Authorization", jwtToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            String jsonText = new String(inputStream.readAllBytes());
            return new JSONObject(jsonText);
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido do agendamento" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-do-agendamento.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException
    {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }
}
