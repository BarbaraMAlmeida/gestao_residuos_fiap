package fiap.com.br.atvd_spring_boot.controllers;

import fiap.com.br.atvd_spring_boot.dto.CaminhaoCadastroDto;
import fiap.com.br.atvd_spring_boot.dto.CaminhaoExibicaoDto;
import fiap.com.br.atvd_spring_boot.services.CaminhaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/caminhao")
public class CaminhaoController {

    @Autowired
    private CaminhaoService caminhaoService;

    //ENDPOINT - LISTAR CAMINHÕES
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CaminhaoExibicaoDto> getAllCaminhoes() {return caminhaoService.getCaminhoes();}

    @PostMapping
    public ResponseEntity<?> createCaminhao(@RequestBody @Valid CaminhaoExibicaoDto caminhaoExibicaoDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors); // retorna 400
        }

        CaminhaoExibicaoDto created = caminhaoService.createCaminhao(caminhaoExibicaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
