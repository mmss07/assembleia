package com.dbc.assembleia.service;

import com.dbc.assembleia.dto.SessaoVotacaoRequestDTO;
import com.dbc.assembleia.dto.SessaoVotacaoResponseDTO;
import com.dbc.assembleia.models.Pauta;
import com.dbc.assembleia.models.SessaoVotacao;
import com.dbc.assembleia.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessaoVotacaoService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaService pautaService;
    private final ModelMapper modelMapper;
    private final VotoSessaoService votoSessaoService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public SessaoVotacaoResponseDTO save(SessaoVotacaoRequestDTO sessaoVotacaoDTO) {

        log.info("SessaoVotacaoService ::  save :: start");
        final var pauta = Optional.ofNullable(pautaService.getPautaById(sessaoVotacaoDTO.getIdPauta()));
        if (pauta.isPresent()) {
            var sessaoVotacao = SessaoVotacao.builder()
                    .dataHoraAbertura(LocalDateTime.now())
                    .dataHoraFechamento(LocalDateTime.now().plusMinutes(sessaoVotacaoDTO.getTempo()))
                    .pauta(pauta.get())
                    .tempo((sessaoVotacaoDTO.getTempo() == 0L) ? 1 : sessaoVotacaoDTO.getTempo())
                    .build();
            final var sessao = sessaoVotacaoRepository.save(sessaoVotacao);
            return SessaoVotacaoResponseDTO.builder().idSessaoVotacao(sessao.getIdSessaoVotacao())
                    .tempo(sessao.getTempo())
                    .idPauta(sessao.getPauta().getIdPauta())
                    .dataHoraAbertura(sessao.getDataHoraAbertura().format(formatter).toString())
                    .dataHoraFechamento(sessao.getDataHoraFechamento().format(formatter).toString())
                    .build();
        }
        log.info("SessaoVotacaoService ::  save :: end");
        return SessaoVotacaoResponseDTO.builder().build();
    }

    public List<SessaoVotacaoResponseDTO> getAllSessoes() {
        log.info("SessaoVotacaoService ::  getAllSessoes");
        var sessaoVotacaoList = sessaoVotacaoRepository.findAll();
        return sessaoVotacaoList.stream()
                .map(x -> {
                    var totalVotosSim = Optional.ofNullable(votoSessaoService.getTotalVotosByIdSessaoSIM(x.getIdSessaoVotacao()));
                    var totalVotosNao = Optional.ofNullable(votoSessaoService.getTotalVotosByIdSessaoNAO(x.getIdSessaoVotacao()));
                    return SessaoVotacaoResponseDTO.builder()
                            .idSessaoVotacao(x.getIdSessaoVotacao())
                            .idPauta(x.getPauta().getIdPauta())
                            .dataHoraAbertura(x.getDataHoraAbertura().format(formatter).toString())
                            .dataHoraFechamento(x.getDataHoraFechamento().format(formatter).toString())
                            .tempo(x.getTempo())
                            .totalVotosSim(totalVotosSim.orElse(0L))
                            .totalVotosNao(totalVotosNao.orElse(0L))
                            .build();})
                .collect(Collectors.toList());
    }

    public  SessaoVotacaoResponseDTO getSessaoVotacaoResponseDTO(Long id) {
        log.info("SessaoVotacaoService ::  getSessaoVotacaoResponseDTO :: id = {}", id);
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
