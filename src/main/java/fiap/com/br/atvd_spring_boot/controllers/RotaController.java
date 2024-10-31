package fiap.com.br.atvd_spring_boot.controllers;

import fiap.com.br.atvd_spring_boot.dto.RotaDto;
import fiap.com.br.atvd_spring_boot.model.Rota;
import fiap.com.br.atvd_spring_boot.services.RotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rotas")
public class RotaController {

    @Autowired
    private RotaService rotaService;

    // Endpoint post
    @PostMapping
    public Rota criarRota(@RequestBody RotaDto rotaDto) {
        return rotaService.criarRota(rotaDto);
    }

    // Endpoint delete
    @DeleteMapping("/{id}")
    public void deletarRota(@PathVariable Long id) {
        rotaService.deletarRota(id);
    }
}



