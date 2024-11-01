package fiap.com.br.atvd_spring_boot.dto;

import fiap.com.br.atvd_spring_boot.model.Usuario;
import fiap.com.br.atvd_spring_boot.model.UsuarioRole;

public record UsuarioExibicaoDto(
        Long usuarioId,
        String nome,
        String email,
        UsuarioRole role
) {

    public UsuarioExibicaoDto(Usuario usuario) {
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
