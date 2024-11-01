package fiap.com.br.atvd_spring_boot.repository;

import fiap.com.br.atvd_spring_boot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public UserDetails findByEmail(String email);
}
