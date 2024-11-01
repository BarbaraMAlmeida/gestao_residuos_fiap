package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.Agendamento;
import fiap.com.br.atvd_spring_boot.model.Rota;
import fiap.com.br.atvd_spring_boot.model.StatusAgendamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AgendamentoCadastroDto(
        Long idAgendamento,

        @NotNull(message = "A data de agendamento é obrigatória")
        LocalDate dtAgendamento,

        @NotNull(message = "O status de agendamento é obrigatório")
        StatusAgendamento statusAgendamento,

        @NotNull(message = "A rota é obrigatória")
        Long rota
) {
    public AgendamentoCadastroDto(Agendamento agendamento) {
        this(
                agendamento.getIdAgendamento(),
                agendamento.getDtAgendamento(),
                agendamento.getStatusAgendamento(),
                agendamento.getRota().getIdRota()
        );
    }
}
