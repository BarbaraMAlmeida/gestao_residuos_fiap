package fiap.com.br.atvd_spring_boot.model;

public enum StatusRecipiente {

    VAZIO("Vazio"),
    QUASE_CHEIO("Quase cheio"),
    CAPACIDADE_ATINGIDA("Capacidade atingida");

    private String status;

    StatusRecipiente(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
