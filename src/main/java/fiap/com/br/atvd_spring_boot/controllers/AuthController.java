package fiap.com.br.atvd_spring_boot.controllers;

import fiap.com.br.atvd_spring_boot.config.security.TokenService;
import fiap.com.br.atvd_spring_boot.dto.LoginDto;
import fiap.com.br.atvd_spring_boot.dto.TokenDto;
import fiap.com.br.atvd_spring_boot.dto.UsuarioCadastroDto;
import fiap.com.br.atvd_spring_boot.dto.UsuarioExibicaoDto;
import fiap.com.br.atvd_spring_boot.model.Usuario;
import fiap.com.br.atvd_spring_boot.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.senha());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDto(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
        UsuarioExibicaoDto usuarioSalvo = null;
        usuarioSalvo = usuarioService.salvarUsuario(usuarioCadastroDto);
        return usuarioSalvo;
    }
}
