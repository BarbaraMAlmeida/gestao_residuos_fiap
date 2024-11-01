package fiap.com.br.atvd_spring_boot.model;

public enum StatusEmergencia {

    EM_ANDAMENTO("Em andamento"),
    COLETADA("Coletada");

    private String status;

    StatusEmergencia(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
//