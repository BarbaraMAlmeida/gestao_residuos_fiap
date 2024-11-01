package fiap.com.br.atvd_spring_boot.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RotaDto(
        @NotNull(message = "A data é obrigatória")
        LocalDate dtRota,
        @NotNull(message = "O recipiente é obrigatório")
        Long recipiente,
        @NotNull(message = "O caminhao é obrigatório")
        Long caminhao) {
}

