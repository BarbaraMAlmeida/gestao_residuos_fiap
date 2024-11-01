package fiap.com.br.atvd_spring_boot.services;

import fiap.com.br.atvd_spring_boot.dto.UsuarioCadastroDto;
import fiap.com.br.atvd_spring_boot.dto.UsuarioExibicaoDto;
import fiap.com.br.atvd_spring_boot.model.Usuario;
import fiap.com.br.atvd_spring_boot.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto salvarUsuario(@Valid UsuarioCadastroDto usuarioCadastroDto) {

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        usuario.setSenha(senhaCriptografada);
        return new UsuarioExibicaoDto(usuarioRepository.save(usuario));
    }
}
