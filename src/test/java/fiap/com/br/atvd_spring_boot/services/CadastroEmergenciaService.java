package fiap.com.br.atvd_spring_boot.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.EmergenciaModel;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

public class CadastroEmergenciaService {

    final EmergenciaModel emergenciaModel = new EmergenciaModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080";
    public void setFieldsDelivery(String field, String value) {
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
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}