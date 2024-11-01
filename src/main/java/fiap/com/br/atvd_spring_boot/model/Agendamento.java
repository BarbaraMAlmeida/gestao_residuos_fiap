package fiap.com.br.atvd_spring_boot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "T_AGENDAMENTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Agendamento {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AUTOMATIC_T_ROTA"
    )
    @SequenceGenerator(
            name = "SEQ_AUTOMATIC_T_ROTA",
            sequenceName = "SEQ_AUTOMATIC_T_ROTA",
            allocationSize = 1
    )
    @Column(name = "id_agendamento")
    private Long idAgendamento;

    @Column(name = "dt_agendamento")
    private LocalDate dtAgendamento;

    @Column(name = "status_agendamento")
    @Enumerated(EnumType.STRING)
    private StatusAgendamento statusAgendamento;

    //Um agendamento está relacionado a apenas uma rota,
    // mas uma rota pode ter vários agendamentos.
    @ManyToOne
    @JoinColumn(name = "T_ROTA_id_rota")
    private Rota rota;

}
