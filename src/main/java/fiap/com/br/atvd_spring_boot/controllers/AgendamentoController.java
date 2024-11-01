package fiap.com.br.atvd_spring_boot.controllers;

import fiap.com.br.atvd_spring_boot.dto.AgendamentoCadastroDto;
import fiap.com.br.atvd_spring_boot.dto.AgendamentoExibicaoDto;
import fiap.com.br.atvd_spring_boot.services.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    // Endpoint para listar todos os agendamentos
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AgendamentoExibicaoDto> getAgendamentos() {
        return agendamentoService.getAgendamentos();
    }

    // Endpoint para criar um novo agendamento
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoExibicaoDto createAgendamento(@RequestBody @Valid AgendamentoCadastroDto agendamentoCadastroDto) {
        return agendamentoService.createAgendamento(agendamentoCadastroDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAgedamento(@PathVariable Long id) {
        agendamentoService.deleteAgedamento(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AgendamentoExibicaoDto updateAgendamento(
            @RequestBody @Valid AgendamentoCadastroDto agendamentoCadastroDto,
            @PathVariable Long id
    ) {
        return agendamentoService.updateAgendamento(agendamentoCadastroDto, id);
    }
}
