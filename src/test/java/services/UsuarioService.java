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
import model.UsuarioModel;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

import org.json.JSONObject;



import static io.restassured.RestAssured.given;

public class UsuarioService {

    final UsuarioModel usuarioModel = new UsuarioModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080";

    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();


    public void setFieldsUsuario(String field, String value) {
        switch (field) {
            case "nome" -> usuarioModel.setNome(value);
            case "email" -> usuarioModel.setEmail(value);
            case "senha" -> usuarioModel.setSenha(value);
            case "role" -> usuarioModel.setRole(value);
            default -> throw new IllegalArgumentException("Campo inesperado: " + field);
        }
    }

    public void createUsuario(String endPoint) {
        String url = baseUrl + endPoint;

        // Garante unicidade nos testes:
        String sufixo = UUID.randomUUID().toString().substring(0, 5);

        usuarioModel.setNome(usuarioModel.getNome() + "_" + sufixo);

        // Proteção contra e-mail inválido
        if (usuarioModel.getEmail() != null && usuarioModel.getEmail().contains("@")) {
            String[] partesEmail = usuarioModel.getEmail().split("@");
            if (partesEmail.length == 2) {
                String emailBase = partesEmail[0];
                String dominio = partesEmail[1];
                usuarioModel.setEmail(emailBase + "+" + sufixo + "@" + dominio);
            }
        }

        String bodyToSend = gson.toJson(usuarioModel);

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }



    public void setFieldsLoginUsuario(String field, String value) {
        switch (field) {
            case "email" -> usuarioModel.setEmail(value);
            case "senha" -> usuarioModel.setSenha(value);
            default -> throw new IllegalArgumentException("Campo inesperado: " + field);
        }
    }


    public void loginUsuario(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(usuarioModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
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
            case "Cadastro bem-sucedido do usuario" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-usuarios.json");
            case "Login bem-sucedido do usuario" -> jsonSchema = loadJsonFromFile(schemasPath + "login-bem-sucedido-de-usuarios.json");
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
