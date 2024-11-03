package fiap.com.br.atvd_spring_boot.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException erro) {
        Map<String, String> mapaDeErro = new HashMap<>();
        List<FieldError> campos = erro.getBindingResult().getFieldErrors();
        for(FieldError campo: campos) {
            mapaDeErro.put(campo.getField(), campo.getDefaultMessage());
        }

        return mapaDeErro;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> manusearIntegridadeDosDados(DataIntegrityViolationException erro) {
        Map<String, String> mapaDeErro = new HashMap<>();

        mapaDeErro.put("erro", "Verifique as informações informadas.");

        return mapaDeErro;
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public Map<String, String> manusearIntegridadeDosDados(InvalidDataAccessResourceUsageException erro) {
        Map<String, String> mapaDeErro = new HashMap<>();

        mapaDeErro.put("erro", "A tabela ou view não existe no banco de dados.");

        return mapaDeErro;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> manusearErroDeLeituraDoCorpo(HttpMessageNotReadableException erro) {
        Map<String, String> mapaDeErro = new HashMap<>();
        mapaDeErro.put("erro", "Preencha todos os campos obrigatórios.");

        return mapaDeErro;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String, String> manusearEntidadeNaoEncontrada(EntityNotFoundException erro) {
        Map<String, String> mapaDeErro = new HashMap<>();
        mapaDeErro.put("erro", "Entidade não encontrada.");
        return mapaDeErro;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Map<String, String> manusearMetodoNaoPermitido(HttpRequestMethodNotSupportedException erro) {
        Map<String, String> mapaDeErro = new HashMap<>();
        mapaDeErro.put("erro", "Método HTTP não suportado.");
        return mapaDeErro;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Map<String, String> manusearAcessoNegado(AccessDeniedException erro) {
        Map<String, String> mapaDeErro = new HashMap<>();
        mapaDeErro.put("erro", "Acesso negado.");
        return mapaDeErro;
    }

}
