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
import model.EmergenciaModel;
import model.LocalDateAdapter;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroEmergenciaService {

    final EmergenciaModel emergenciaModel = new EmergenciaModel();
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
           "email": "andre@teste3.com",
           "senha": "12345678"
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

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            String jsonText = new String(inputStream.readAllBytes());
            return new JSONObject(jsonText);
        }
    }


    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido da Emergencia" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-da-emergencia.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public void setFieldsEmergencia(String field, String value) {
        switch (field) {
        case "dtEmergencia" -> emergenciaModel.setDtEmergencia(LocalDate.parse(value));
        case "status" -> emergenciaModel.setStatus(value);
        case "descricao" -> emergenciaModel.setDescricao(value);
        case "recipiente" -> emergenciaModel.setRecipiente(Integer.parseInt(value));
        case "caminhao" -> emergenciaModel.setCaminhao(Integer.parseInt(value));
        default -> throw new IllegalArgumentException("Campo inválido: " + field);
        }
    }

     public void createEmergencia(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(emergenciaModel);
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

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException
    {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }

    public void listEmergencias (String endpoint) {
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

}