package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.StatusRecipiente;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;

import java.time.LocalDate;

public class RecipienteDto {

    private Long maxCapacidade;

    private Long atualNivel;

    @Max(20)
    private StatusRecipiente status;

    private LocalDate ultimaAtualizacao;

    private Long latitude;

    private Long longitude;
}
