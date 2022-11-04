package com.dbc.assembleia.service;

import com.dbc.assembleia.dto.VotoSessaoRequestDTO;
import com.dbc.assembleia.dto.VotoSessaoResponseDTO;
import com.dbc.assembleia.models.SessaoVotacao;
import com.dbc.assembleia.models.VotoSessao;
import com.dbc.assembleia.repository.SessaoVotacaoRepository;
import com.dbc.assembleia.repository.VotoSessaoRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotoSessaoService {

    private final VotoSessaoRepository votoSessaoRepository;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final ModelMapper modelMapper;

    public VotoSessaoResponseDTO save(VotoSessaoRequestDTO votoSessaoRequestDTO) {
        log.info("VotoSessaoService ::  save :: cpf = {}", votoSessaoRequestDTO.getCpf());
        final var sessaoVotacao = sessaoVotacaoRepository.findById(votoSessaoRequestDTO.getIdSessaoVotacao());
        if (sessaoVotacao.isPresent() && validaVoto(votoSessaoRequestDTO, sessaoVotacao.get())) {
            return modelMapper.map(votoSessaoRepository.save(VotoSessao.builder()
                            .idSessaoVotacao(votoSessaoRequestDTO.getIdSessaoVotacao())
                            .cpf(votoSessaoRequestDTO.getCpf())
                            .voto(votoSessaoRequestDTO.getVoto()).build())
                    , VotoSessaoResponseDTO.class);
        }
        return VotoSessaoResponseDTO.builder().build();
    }

    public long getTotalVotosByIdSessaoSIM(Long idSessaoVoto) {
        log.info("VotoSessaoService ::  getTotalVotosByIdSessaoSIM :: idSessaoVoto = {}", idSessaoVoto);
        return  votoSessaoRepository.findByIdSessaoVotacao(idSessaoVoto).stream().filter(x -> x.getVoto().equals("SIM")).count();
    }

    public long getTotalVotosByIdSessaoNAO(Long idSessaoVoto) {
        log.info("VotoSessaoService ::  getTotalVotosByIdSessaoNAO :: idSessaoVoto = {}", idSessaoVoto);
        return  votoSessaoRepository.findByIdSessaoVotacao(idSessaoVoto).stream().filter(x -> x.getVoto().equals("NAO")).count();
    }

    private boolean validaVoto(VotoSessaoRequestDTO votoSessaoRequestDTO, SessaoVotacao sessaoVotacao) {
        log.info("VotoSessaoService ::  validaVoto :: cpf = {}", votoSessaoRequestDTO.getCpf());
        return (((LocalDateTime.now().isBefore(sessaoVotacao.getDataHoraFechamento()))
                || (LocalDateTime.now().isEqual(sessaoVotacao.getDataHoraFechamento())))
                && (LocalDateTime.now().isAfter(sessaoVotacao.getDataHoraAbertura()))
                && (votoSessaoRepository.findByCpfAndIdSessaoVotacao(votoSessaoRequestDTO.getCpf(), votoSessaoRequestDTO.getIdSessaoVotacao()) == null));
    }

}
