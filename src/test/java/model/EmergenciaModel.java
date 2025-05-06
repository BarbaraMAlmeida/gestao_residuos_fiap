package model;
import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmergenciaModel {
    @Expose
    private LocalDate dtEmergencia;
    @Expose
    private String status;
    @Expose
    private String descricao;
    @Expose
    private int recipiente;
    @Expose
    private int caminhao;
}