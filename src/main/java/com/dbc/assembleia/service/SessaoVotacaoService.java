package com.dbc.assembleia.service;

import com.dbc.assembleia.dto.SessaoVotacaoRequestDTO;
import com.dbc.assembleia.dto.SessaoVotacaoResponseDTO;
import com.dbc.assembleia.models.Pauta;
import com.dbc.assembleia.models.SessaoVotacao;
import com.dbc.assembleia.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessaoVotacaoService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaService pautaService;
    private final ModelMapper modelMapper;
    private final VotoSessaoService votoSessaoService;

    public void save(SessaoVotacaoRequestDTO sessaoVotacaoDTO) {
        final var pauta = Optional.ofNullable(pautaService.getPautaById(sessaoVotacaoDTO.getIdPauta()));
        if (pauta.isPresent()) {
            var sessaoVotacao = SessaoVotacao.builder()
                    .dataHoraAbertura(LocalDateTime.now())
                    .dataHoraFechamento(LocalDateTime.now().plusMinutes(sessaoVotacaoDTO.getTempo()))
                    .pauta(pauta.get())
                    .tempo((sessaoVotacaoDTO.getTempo() == 0L) ? 1 : sessaoVotacaoDTO.getTempo())
                    .build();
            sessaoVotacaoRepository.save(sessaoVotacao);
        }


    }

    public List<SessaoVotacaoResponseDTO> getAllSessoes() {
        var sessaoVotacaoList = sessaoVotacaoRepository.findAll();
        return sessaoVotacaoList.stream()
                .map(x -> {
                    var totalVotosSim = Optional.ofNullable(votoSessaoService.getTotalVotosByIdSessaoSIM(x.getIdSessaoVotacao()));
                    var totalVotosNao = Optional.ofNullable(votoSessaoService.getTotalVotosByIdSessaoNAO(x.getIdSessaoVotacao()));
                    return SessaoVotacaoResponseDTO.builder()
                            .idSessaoVotacao(x.getIdSessaoVotacao())
                            .idPauta(x.getPauta().getIdPauta())
                            .dataHoraAbertura(x.getDataHoraAbertura())
                            .dataHoraFechamento(x.getDataHoraFechamento())
                            .tempo(x.getTempo())
                            .totalVotosSim(totalVotosSim.orElse(0L))
                            .totalVotosNao(totalVotosNao.orElse(0L))
                            .build();})
                .collect(Collectors.toList());
    }

    public  SessaoVotacaoResponseDTO getSessaoVotacaoResponseDTO(Long id) {
        var byId = sessaoVotacaoRepository.findById(id);
        if (byId.isPresent()){
            final var sessao = modelMapper.map(byId.get(), SessaoVotacaoResponseDTO.class);
            var totalVotosSim = votoSessaoService.getTotalVotosByIdSessaoSIM(sessao.getIdSessaoVotacao());
            var totalVotosNao = votoSessaoService.getTotalVotosByIdSessaoNAO(sessao.getIdSessaoVotacao());
            sessao.setTotalVotosSim(totalVotosSim);
            sessao.setTotalVotosNao(totalVotosNao);
        }

        return SessaoVotacaoResponseDTO.builder().build();
    }

}
