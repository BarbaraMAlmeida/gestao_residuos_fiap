package fiap.com.br.atvd_spring_boot.services;

import fiap.com.br.atvd_spring_boot.dto.AgendamentoCadastroDto;
import fiap.com.br.atvd_spring_boot.dto.AgendamentoExibicaoDto;
import fiap.com.br.atvd_spring_boot.model.Agendamento;
import fiap.com.br.atvd_spring_boot.repository.AgendamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    // Método para listar todos os agendamentos
    public List<AgendamentoExibicaoDto> getAgendamentos() {
        return agendamentoRepository.findAll()
                .stream()
                .map(AgendamentoExibicaoDto::new)
                .collect(Collectors.toList());
    }

    // Método para criar um novo agendamento
    public AgendamentoExibicaoDto createAgendamento(AgendamentoCadastroDto agendamentoCadastroDto) {
        Agendamento agendamento = new Agendamento();
        BeanUtils.copyProperties(agendamentoCadastroDto, agendamento);
        return new AgendamentoExibicaoDto(agendamentoRepository.save(agendamento));
    }
}
