package fiap.com.br.atvd_spring_boot.repository;

import fiap.com.br.atvd_spring_boot.model.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {
}
