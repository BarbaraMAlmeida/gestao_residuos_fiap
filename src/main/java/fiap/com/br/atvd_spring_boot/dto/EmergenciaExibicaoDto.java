package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.Emergencia;
import fiap.com.br.atvd_spring_boot.model.StatusEmergencia;

import java.time.LocalDate;

public record EmergenciaExibicaoDto(
        LocalDate dtEmergencia,

        StatusEmergencia status,

        String descricao,

        Long recipiente,

        Long caminhao

) {
    public EmergenciaExibicaoDto(
            Emergencia emergencia
    ) {
        this(
                emergencia.getDtEmergencia(), emergencia.getStatus(), emergencia.getDescricao(),
                emergencia.getRecipiente().getIdRecipiente(), emergencia.getCaminhao().getIdCaminhao()
        );
    }
}
