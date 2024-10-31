package fiap.com.br.atvd_spring_boot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_caminhao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Caminhao {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_AUTOMATIC_T_CAMINHAO"
    )
    @SequenceGenerator(
            name = "SEQ_AUTOMATIC_T_CAMINHAO",
            sequenceName = "SEQ_AUTOMATIC_T_CAMINHAO",
            allocationSize = 1
    )
    @Column(name = "id_caminhao")
    private Long idCaminhao;

    @Column(name = "ds_placa")
    private String placa;

    @Column(name = "ds_capacidade")
    private Long capacidade;

}
