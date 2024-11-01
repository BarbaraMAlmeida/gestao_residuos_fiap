package fiap.com.br.atvd_spring_boot.services;

import fiap.com.br.atvd_spring_boot.dto.AgendamentoCadastroDto;
import fiap.com.br.atvd_spring_boot.dto.AgendamentoExibicaoDto;
import fiap.com.br.atvd_spring_boot.exception.InformacaoNaoEncontradaException;
import fiap.com.br.atvd_spring_boot.model.Agendamento;
import fiap.com.br.atvd_spring_boot.model.Caminhao;
import fiap.com.br.atvd_spring_boot.model.Recipiente;
import fiap.com.br.atvd_spring_boot.model.Rota;
import fiap.com.br.atvd_spring_boot.repository.AgendamentoRepository;
import fiap.com.br.atvd_spring_boot.repository.RotaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;


    @Autowired
    private RotaRepository rotaRepository;

    //Método para listar todos os agendamentos
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
        Rota rota = rotaRepository.findById(agendamentoCadastroDto.rota())
                .orElseThrow(() -> new RuntimeException("Rota não encontrada"));
        agendamento.setRota(rota);
        return new AgendamentoExibicaoDto(agendamentoRepository.save(agendamento));
    }

    public void deleteAgedamento(Long id) {
        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(id);

        if(agendamentoOptional.isPresent()) {
            agendamentoRepository.delete(agendamentoOptional.get());
        }
        else {
            throw new InformacaoNaoEncontradaException("Esse agendamento não existe");
        }
    }

    public AgendamentoExibicaoDto updateAgendamento(
            @Valid AgendamentoCadastroDto agendamentoCadastroDto,
            Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new InformacaoNaoEncontradaException("Agendamento não encontrado"));

       agendamento.setDtAgendamento(agendamentoCadastroDto.dtAgendamento());
       agendamento.setStatusAgendamento(agendamentoCadastroDto.statusAgendamento());
        Rota rota = rotaRepository.findById(agendamentoCadastroDto.rota())
                .orElseThrow(() -> new RuntimeException("Rota não encontrada"));
        agendamento.setRota(rota);
        return new AgendamentoExibicaoDto(agendamentoRepository.save(agendamento));
    }
}
