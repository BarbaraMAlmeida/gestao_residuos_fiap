package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.StatusEmergencia;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class CaminhaoDto {

    @NotBlank
    @Max(7)
    private String placa;

    @NotBlank
    private Long capacidade;

}
