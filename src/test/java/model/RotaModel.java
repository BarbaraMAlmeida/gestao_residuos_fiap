package model;
import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RotaModel {
    @Expose(serialize = false)
    private Long idRota;
    @Expose
    private LocalDate dtRota;
    @Expose
    private Long caminhao;
    @Expose
    private Long recipiente;
}