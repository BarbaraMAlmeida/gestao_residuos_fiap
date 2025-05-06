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
import model.CaminhaoModel;
import model.LocalDateAdapter;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CaminhaoService {

    final CaminhaoModel caminhaoModel = new CaminhaoModel();
    public final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
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


    public void setFieldsCaminhao(String field, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Valor nulo ou vazio para o campo: " + field);
        }

        switch (field.trim()) {
            case "placa" ->
                    caminhaoModel.setPlaca(value);

            case "capacidade" ->
                    caminhaoModel.setCapacidade(Long.parseLong(value));
            default -> throw new IllegalArgumentException("Campo inesperado: " + field);
        }
    }

    public void createCaminhao(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(caminhaoModel);
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

    public void listCaminhao (String endpoint) {
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
            case "Cadastro bem-sucedido do caminhao" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-do-caminhao.json");
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
