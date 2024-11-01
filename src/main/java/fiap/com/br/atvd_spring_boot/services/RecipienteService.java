package fiap.com.br.atvd_spring_boot.services;

import fiap.com.br.atvd_spring_boot.dto.RecipienteCadastroDto;
import fiap.com.br.atvd_spring_boot.dto.RecipienteExibicaoDto;
import fiap.com.br.atvd_spring_boot.model.Recipiente;
import fiap.com.br.atvd_spring_boot.repository.RecipienteRepository;
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


    public List<RecipienteExibicaoDto> getRecipientes() {
        return recipienteRepository.findAll()
                .stream()
                .map(RecipienteExibicaoDto::new)
                .collect(Collectors.toList());
    }

    public RecipienteExibicaoDto createRecipiente(RecipienteCadastroDto recipienteCadastroDto) {
        Recipiente recipiente = new Recipiente();
        BeanUtils.copyProperties(recipienteCadastroDto, recipiente);

        return new RecipienteExibicaoDto(recipienteRepository.save(recipiente));
    }
}
