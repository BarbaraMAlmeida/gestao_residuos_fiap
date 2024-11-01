package fiap.com.br.atvd_spring_boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformacaoNaoEncontradaException extends RuntimeException {

    public InformacaoNaoEncontradaException(String message) {
        super(message);
    }

}
