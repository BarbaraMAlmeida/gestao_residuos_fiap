package fiap.com.br.atvd_spring_boot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "T_EMERGENCIA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Emergencia {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AUTOMATIC_T_EMERGENCIA"
    )
    @SequenceGenerator(
            name = "SEQ_AUTOMATIC_T_EMERGENCIA",
            sequenceName = "SEQ_AUTOMATIC_T_EMERGENCIA",
            allocationSize = 1
    )
    @Column(name = "id_emergencia")
    private Long idEmergencia;

    @Column(name = "dt_emergencia")
    private LocalDate dtEmergencia;

    @Column(name = "status")
    private StatusEmergencia status;

    @Column(name = "ds_emergencia")
    private String descricao;

    //Um recipiente pode estar relacionado a várias emergências,
    // mas cada emergência está associada a apenas um recipiente.
    @ManyToOne
    @JoinColumn(name = "T_RECIPIENTE_id_recipiente")
    private Recipiente recipiente;

    // Um caminhão pode estar envolvido em várias emergências,
    // mas cada emergência está associada a apenas um caminhão.
    @ManyToOne
    @JoinColumn(name = "T_CAMINHAO_id_caminhao")
    private Caminhao caminhao;


}
