package fiap.com.br.atvd_spring_boot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @NotBlank(message = "E-mail é obrigatório!")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória!")
        @Size(max = 10, min = 6, message = "A senha deve ter entre 6 e 10 caracteres")
        String senha
) {


}