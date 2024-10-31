package fiap.com.br.atvd_spring_boot.dto;

import java.time.LocalDate;

public record RotaDto(LocalDate dtRota, Long idRecipiente, Long idCaminhao) {
}

