package fiap.com.br.atvd_spring_boot.services;

import fiap.com.br.atvd_spring_boot.dto.RotaDto;
import fiap.com.br.atvd_spring_boot.model.Caminhao;
import fiap.com.br.atvd_spring_boot.model.Recipiente;
import fiap.com.br.atvd_spring_boot.model.Rota;
import fiap.com.br.atvd_spring_boot.repository.CaminhaoRepository;
import fiap.com.br.atvd_spring_boot.repository.RecipienteRepository;
import fiap.com.br.atvd_spring_boot.repository.RotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RotaService {

    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private CaminhaoRepository caminhaoRepository;

    @Autowired
    private RecipienteRepository recipienteRepository;

    public Rota criarRota(RotaDto rotaDto) {
        Recipiente recipiente = recipienteRepository.findById(rotaDto.idRecipiente())
                .orElseThrow(() -> new RuntimeException("Recipiente não encontrado"));
        Caminhao caminhao = caminhaoRepository.findById(rotaDto.idCaminhao())
                .orElseThrow(() -> new RuntimeException("Caminhão não encontrado"));

        Rota rota = new Rota();
        rota.setDtRota(rotaDto.dtRota());
        rota.setRecipiente(recipiente);
        rota.setCaminhao(caminhao);
        return rotaRepository.save(rota);
    }

    public void deletarRota(Long id) {
        if (rotaRepository.existsById(id)) {
            rotaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rota não encontrada");
        }
    }
}

