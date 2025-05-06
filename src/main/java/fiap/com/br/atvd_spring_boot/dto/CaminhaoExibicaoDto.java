package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.Caminhao;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CaminhaoExibicaoDto(
        @NotBlank(message = "O valor da placa é obrigatório")
        @Size(max = 7, message = "A placa deve conter no máximo 7 caracteres")
        String placa,
        @NotNull(message = "O valor da capacidade é obrigatório")
        Long capacidade
) {
    public CaminhaoExibicaoDto(Caminhao caminhao) {
        this(
                caminhao.getPlaca(),
                caminhao.getCapacidade()
        );
    }
}