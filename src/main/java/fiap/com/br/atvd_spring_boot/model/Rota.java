package fiap.com.br.atvd_spring_boot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "T_ROTA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Rota {

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
    @Column(name = "id_rota")
    private Long idRota;

    @Column(name = "dt_rota")
    private LocalDate dtRota;

    // Um caminhão pode ter várias rotas planejadas ao longo de
    // um período (várias viagens), mas cada rota só pode ser feita por um único caminhão.
    @ManyToOne
    @JoinColumn(name = "T_CAMINHAO_id_caminhao")
    private Caminhao caminhao;

    //Um recipiente pode ser alvo de várias rotas diferentes em datas distintas,
    // mas cada rota está associada a um único recipiente.
    @ManyToOne
    @JoinColumn(name = "T_RECIPIENTE_id_recipiente")
    private Recipiente recipiente;

}
