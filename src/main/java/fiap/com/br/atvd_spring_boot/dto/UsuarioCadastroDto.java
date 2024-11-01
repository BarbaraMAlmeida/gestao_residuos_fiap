package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDto (
        Long usuarioId,

        @NotBlank(message = "O campo nome é obrigatório!")
        String nome,

        @NotBlank(message = "E-mail é obrigatório!")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória!")
        @Size(max = 10, min = 6, message = "A senha deve ter entre 6 e 10 caracteres")
        String senha,

        UsuarioRole role

) {

}

