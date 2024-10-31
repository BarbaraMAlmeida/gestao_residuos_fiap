package fiap.com.br.atvd_spring_boot.services;

import fiap.com.br.atvd_spring_boot.dto.EmergenciaCadastroDto;
import fiap.com.br.atvd_spring_boot.dto.EmergenciaExibicaoDto;
import fiap.com.br.atvd_spring_boot.model.Caminhao;
import fiap.com.br.atvd_spring_boot.model.Emergencia;
import fiap.com.br.atvd_spring_boot.model.Recipiente;
import fiap.com.br.atvd_spring_boot.repository.CaminhaoRepository;
import fiap.com.br.atvd_spring_boot.repository.EmergenciaRepository;
import fiap.com.br.atvd_spring_boot.repository.RecipienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmergenciaService {

    @Autowired
    private EmergenciaRepository emergenciaRepository;
    @Autowired
    private CaminhaoRepository caminhaoRepository;
    @Autowired
    private RecipienteRepository recipienteRepository;

    public List<EmergenciaExibicaoDto> findAll() {
        return emergenciaRepository.findAll().stream()
                .map(EmergenciaExibicaoDto::new)
                .collect(Collectors.toList());

    }

    public EmergenciaExibicaoDto createEmergencia(EmergenciaCadastroDto emergenciaDto) {
        Emergencia emergencia = new Emergencia();
        BeanUtils.copyProperties(emergenciaDto, emergencia);
        Recipiente recipiente = recipienteRepository.findById(emergenciaDto.recipiente())
                .orElseThrow(() -> new RuntimeException("Recipiente não encontrado"));
        Caminhao caminhao = caminhaoRepository.findById(emergenciaDto.caminhao())
                .orElseThrow(() -> new RuntimeException("Caminhão não encontrado"));
        emergencia.setRecipiente(recipiente);
        emergencia.setCaminhao(caminhao);
        return new EmergenciaExibicaoDto(emergenciaRepository.save(emergencia));
    }
}