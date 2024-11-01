package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.Caminhao;

public record CaminhaoExibicaoDto(
        String placa,
        Long capacidade
) {
    public CaminhaoExibicaoDto(Caminhao caminhao) {
        this(
                caminhao.getPlaca(),
                caminhao.getCapacidade()
        );
    }
}