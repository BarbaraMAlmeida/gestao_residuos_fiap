package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.Agendamento;
import fiap.com.br.atvd_spring_boot.model.StatusAgendamento;

import java.time.LocalDate;

public record AgendamentoExibicaoDto(
        Long idAgendamento,
        LocalDate dtAgendamento,
        StatusAgendamento statusAgendamento,
        Long rota
) {
    public AgendamentoExibicaoDto(Agendamento agendamento) {
        this(
                agendamento.getIdAgendamento(),
                agendamento.getDtAgendamento(),
                agendamento.getStatusAgendamento(),
                agendamento.getRota().getIdRota()
        );
    }
}
