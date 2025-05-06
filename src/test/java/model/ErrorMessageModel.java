package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ErrorMessageModel {
    private String erro;

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}