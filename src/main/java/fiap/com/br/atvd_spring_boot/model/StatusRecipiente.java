package fiap.com.br.atvd_spring_boot.model;

public enum StatusRecipiente {

    VAZIO("Vazio"),
    QUASECHEIO("Quase cheio"),
    CAPACIDADEATINGIDA("Capacidade atingida");

    private String status;

    StatusRecipiente(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
