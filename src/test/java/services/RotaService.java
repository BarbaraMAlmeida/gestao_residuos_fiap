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
import model.RotaModel;
import model.LocalDateAdapter;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class RotaService {

    String idRota;

    final RotaModel rotaModel = new RotaModel();
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
            case "Cadastro bem-sucedido da Rota" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-da-rota.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public void setFieldsRotas(String field, String value) {
        switch (field) {
            case "dtRota" -> rotaModel.setDtRota(LocalDate.parse(value));
            case "recipiente" -> rotaModel.setRecipiente(Long.parseLong(value));
            case "caminhao" -> rotaModel.setCaminhao(Long.parseLong(value));
            default -> throw new IllegalArgumentException("Campo inválido: " + field);
        }
    }

    public void createRota(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(rotaModel);
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

    public void deleteRota(String endPoint) {
        String url = String.format("%s%s/%s", baseUrl, endPoint, idRota);
        response = given()
                .header("Authorization", jwtToken)
                .accept(ContentType.JSON)
                .when()
                .delete(url)
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

    public void retrieveRotaId() {
        idRota = String.valueOf(gson.fromJson(response.jsonPath().prettify(), RotaModel.class).getIdRota());
    }
}
