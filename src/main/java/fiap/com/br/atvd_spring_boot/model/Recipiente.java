package fiap.com.br.atvd_spring_boot.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_recipiente")
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Recipiente {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AUTOMATIC_T_RECIPIENTE"
    )
    @SequenceGenerator(
            name = "SEQ_AUTOMATIC_T_RECIPIENTE",
            sequenceName = "SEQ_AUTOMATIC_T_RECIPIENTE",
            allocationSize = 1
    )
    @Column(name = "id_recipiente")
    private Long idRecipiente;

    @Column(name = "max_capacidade")
    private Long maxCapacidade;

    @Column(name = "atual_nvl")
    private Long atualNivel;

    @Column(name = "status")
    private StatusRecipiente status;

    @Column(name = "ultima_att")
    private LocalDate ultimaAtualizacao;

    @Column(name = "latitude_recip")
    private Long latitude;

    @Column(name = "longitude_recip")
    private Long longitude;


}
