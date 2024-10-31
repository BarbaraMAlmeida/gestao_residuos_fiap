package fiap.com.br.atvd_spring_boot.controllers;

import fiap.com.br.atvd_spring_boot.dto.CaminhaoCadastroDto;
import fiap.com.br.atvd_spring_boot.dto.CaminhaoExibicaoDto;
import fiap.com.br.atvd_spring_boot.services.CaminhaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CaminhaoController {

    @Autowired
    private CaminhaoService caminhaoService;

    //ENDPOINT - LISTAR CAMINHÕES
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CaminhaoExibicaoDto> getAllCaminhoes() {return caminhaoService.getCaminhoes();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CaminhaoExibicaoDto createCaminhao(@RequestBody @Valid CaminhaoCadastroDto caminhaoCadastroDto) {
        return caminhaoService.createCaminhao(caminhaoCadastroDto);
    }

}
