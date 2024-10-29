package fiap.com.br.atvd_spring_boot.repository;

import fiap.com.br.atvd_spring_boot.model.Recipiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipienteRepository extends JpaRepository<Recipiente, Long> {

}
