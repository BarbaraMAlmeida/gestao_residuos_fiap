package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.Caminhao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CaminhaoCadastroDto(
        Long idCaminhao,
        @NotBlank(message = "O valor da placa é obrigatório")
        @Max(value = 7, message = "A placa deve conter no máximo 7 caracteres")
        String placa,
        @NotNull(message = "O valor da capacidade é obrigatório")
        Long capacidade

        ) {
    public CaminhaoCadastroDto(Caminhao caminhao) {
        this(
                caminhao.getIdCaminhao(),
                caminhao.getPlaca(),
                caminhao.getCapacidade()
                );
    }
}
