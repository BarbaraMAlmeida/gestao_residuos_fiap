package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.Emergencia;
import fiap.com.br.atvd_spring_boot.model.StatusEmergencia;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmergenciaCadastroDto(
Long idEmergencia,

@NotNull(message = "O valor da data é obrigatório")
LocalDate dtEmergencia,
@NotNull(message = "O valor do status é obrigatório")
StatusEmergencia status,
@NotNull(message = "O valor da desc é obrigatório")
String descricao,
@NotNull(message = "O valor do recipiente é obrigatório")
Long recipiente,
@NotNull(message = "O valor do caminhao é obrigatório")
Long caminhao
) {
    public EmergenciaCadastroDto(
            Emergencia emergencia
    ) {
        this(
                emergencia.getIdEmergencia(), emergencia.getDtEmergencia(), emergencia.getStatus(), emergencia.getDescricao(),
                emergencia.getRecipiente().getIdRecipiente(), emergencia.getCaminhao().getIdCaminhao()
        );
    }
}
