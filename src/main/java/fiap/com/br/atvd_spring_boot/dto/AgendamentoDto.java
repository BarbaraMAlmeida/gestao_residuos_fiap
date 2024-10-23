package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.StatusAgendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;

import java.time.LocalDate;

public class AgendamentoDto {

    private LocalDate dtAgendamento;

    @Max(20)
    private StatusAgendamento statusAgendamento;

    private Long idRota;
}
