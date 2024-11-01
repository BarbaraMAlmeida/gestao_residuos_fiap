package fiap.com.br.atvd_spring_boot.controllers;


import fiap.com.br.atvd_spring_boot.dto.EmergenciaCadastroDto;
import fiap.com.br.atvd_spring_boot.dto.EmergenciaExibicaoDto;
import fiap.com.br.atvd_spring_boot.services.EmergenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergencia")
public class EmergenciaController {

    @Autowired
    private EmergenciaService emergenciaService;

    // Endpoint GET para listar todas as emergências
    @GetMapping
    public List<EmergenciaExibicaoDto> getAllEmergencias() {
        return emergenciaService.findAll();
    }

    @PostMapping
    public EmergenciaExibicaoDto createEmergencia(
            @RequestBody @Valid EmergenciaCadastroDto emergenciaDto
    ){
        return emergenciaService.createEmergencia(emergenciaDto);
    }
}

