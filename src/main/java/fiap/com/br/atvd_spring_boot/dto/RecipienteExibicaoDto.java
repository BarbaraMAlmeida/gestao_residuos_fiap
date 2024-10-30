package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.Recipiente;
import fiap.com.br.atvd_spring_boot.model.StatusRecipiente;

import java.time.LocalDate;

public record RecipienteExibicaoDto(
        Long maxCapacidade,
        Long atualNivel,
        StatusRecipiente status,
        LocalDate ultimaAtualizacao,
        Long latitude,
        Long longitude
) {
    public RecipienteExibicaoDto(Recipiente recipiente) {
        this(
                recipiente.getMaxCapacidade(),
                recipiente.getAtualNivel(),
                recipiente.getStatus(),
                recipiente.getUltimaAtualizacao(),
                recipiente.getLatitude(),
                recipiente.getLongitude()
        );
    }
}
