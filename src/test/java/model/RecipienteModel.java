package model;
import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecipienteModel {
   private Long idRecipiente;
    @Expose
    private Long maxCapacidade;
    @Expose
    private Long atualNivel;
    @Expose
    private String status;
    @Expose
    private LocalDate ultimaAtualizacao;
    @Expose
    private Long latitude;
    @Expose
    private Long longitude;
}