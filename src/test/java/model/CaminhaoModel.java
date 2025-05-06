package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CaminhaoModel {

    @Expose(serialize = false)
    private Long idCaminhao;
    @Expose
    private  String placa;
    @Expose
    private   Long capacidade;

}
