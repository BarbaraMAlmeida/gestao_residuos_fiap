package fiap.com.br.atvd_spring_boot.controllers;

import fiap.com.br.atvd_spring_boot.dto.RecipienteCadastroDto;
import fiap.com.br.atvd_spring_boot.dto.RecipienteExibicaoDto;
import fiap.com.br.atvd_spring_boot.services.RecipienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipiente")
public class RecipienteController {

    @Autowired
    private RecipienteService recipienteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RecipienteExibicaoDto> getRecipientes() {
        return recipienteService.getRecipientes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipienteExibicaoDto createRecipiente(@RequestBody @Valid RecipienteCadastroDto recipienteCadastroDto) {
        return recipienteService.createRecipiente(recipienteCadastroDto);
    }

}
