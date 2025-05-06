package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AgendamentoModel {

    @Expose(serialize = false)
    private int idAgendamento;

    @Expose
    private LocalDate dtAgendamento;

    @Expose
    private String statusAgendamento;

    @Expose
    private Long rota;
}
