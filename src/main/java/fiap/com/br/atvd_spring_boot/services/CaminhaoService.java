package fiap.com.br.atvd_spring_boot.services;

import fiap.com.br.atvd_spring_boot.dto.CaminhaoCadastroDto;
import fiap.com.br.atvd_spring_boot.dto.CaminhaoExibicaoDto;
import fiap.com.br.atvd_spring_boot.model.Caminhao;
import fiap.com.br.atvd_spring_boot.repository.CaminhaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaminhaoService {

    @Autowired
    private CaminhaoRepository caminhaoRepository;


    public List<CaminhaoExibicaoDto> getCaminhoes() {
        return caminhaoRepository.findAll()
                .stream()
                .map(CaminhaoExibicaoDto::new)
                .collect(Collectors.toList());
    }

    public CaminhaoExibicaoDto createCaminhao(CaminhaoCadastroDto caminhaoCadastroDto) {
        Caminhao caminhao = new Caminhao();
        BeanUtils.copyProperties(caminhaoCadastroDto, caminhao);
        return new CaminhaoExibicaoDto(caminhaoRepository.save(caminhao));
    }
}
