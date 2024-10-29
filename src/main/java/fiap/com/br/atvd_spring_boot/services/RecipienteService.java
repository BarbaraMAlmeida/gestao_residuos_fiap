package fiap.com.br.atvd_spring_boot.services;

import fiap.com.br.atvd_spring_boot.dto.RecipienteDto;
import fiap.com.br.atvd_spring_boot.model.Recipiente;
import fiap.com.br.atvd_spring_boot.repository.RecipienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipienteService {

    @Autowired
    private RecipienteRepository recipienteRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<RecipienteDto> getRecipientes() {
        List<Recipiente> recipientes = recipienteRepository.findAll();
        return recipientes.stream()
                .map(recipiente -> modelMapper.map(recipiente, RecipienteDto.class))
                .collect(Collectors.toList());
    }

    public RecipienteDto createRecipiente(@Valid RecipienteDto recipienteDto) {
        Recipiente recipiente = new Recipiente();
        BeanUtils.copyProperties(recipienteDto, recipiente);
        return new RecipienteDto(recipienteRepository.save(recipiente));
    }
}
