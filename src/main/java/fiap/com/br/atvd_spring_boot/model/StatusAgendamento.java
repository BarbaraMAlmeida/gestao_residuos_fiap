package fiap.com.br.atvd_spring_boot.model;

public enum StatusAgendamento {
    REALIZADO("Realizado"),
    CONCLUIDO("Concluido");

    private String status;

    StatusAgendamento(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
