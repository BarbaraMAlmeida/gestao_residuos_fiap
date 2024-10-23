package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.StatusEmergencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class EmergenciaDto {

    private LocalDate dtEmergencia;

    @Max(20)
    private StatusEmergencia status;

    private String descricao;

    private Long idRecipiente;

    private Long idCaminhao;
}
