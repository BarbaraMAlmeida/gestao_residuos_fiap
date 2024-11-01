package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.StatusRecipiente;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record RecipienteCadastroDto(
         Long idRecipiente,
         @NotNull(message = "O valor da capacidade máxima é obrigatório")
         Long maxCapacidade,
         @NotNull(message = "O valor do nível atual é obrigatório")
         Long atualNivel,
         @NotNull(message = "O status é obrigatório")
         StatusRecipiente status,
         @NotNull(message = "A data é obrigatória")
         LocalDate ultimaAtualizacao,
         @NotNull(message = "A latitude é obrigatória")
         Long latitude,
         @NotNull(message = "A longitude é obrigatória")
         Long longitude
) {
}
