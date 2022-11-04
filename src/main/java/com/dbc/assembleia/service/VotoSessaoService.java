package com.dbc.assembleia.service;

import com.dbc.assembleia.dto.VotoSessaoRequestDTO;
import com.dbc.assembleia.dto.VotoSessaoResponseDTO;
import com.dbc.assembleia.models.SessaoVotacao;
import com.dbc.assembleia.models.VotoSessao;
import com.dbc.assembleia.repository.SessaoVotacaoRepository;
import com.dbc.assembleia.repository.VotoSessaoRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VotoSessaoService {

    private final VotoSessaoRepository votoSessaoRepository;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final ModelMapper modelMapper;
    //private final ValidaCpfService validaCpfService;

    public VotoSessaoResponseDTO save(VotoSessaoRequestDTO votoSessaoRequestDTO) {
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
        return  votoSessaoRepository.findByIdSessaoVotacao(idSessaoVoto).stream().filter(x -> x.getVoto().equals("SIM")).count();
    }

    public long getTotalVotosByIdSessaoNAO(Long idSessaoVoto) {
        return  votoSessaoRepository.findByIdSessaoVotacao(idSessaoVoto).stream().filter(x -> x.getVoto().equals("NAO")).count();
    }

    private boolean validaVoto(VotoSessaoRequestDTO votoSessaoRequestDTO, SessaoVotacao sessaoVotacao) {
        return (((LocalDateTime.now().isBefore(sessaoVotacao.getDataHoraFechamento()))
                || (LocalDateTime.now().isEqual(sessaoVotacao.getDataHoraFechamento())))
                && (LocalDateTime.now().isAfter(sessaoVotacao.getDataHoraAbertura()))
                && (votoSessaoRepository.findByCpfAndIdSessaoVotacao(votoSessaoRequestDTO.getCpf(), votoSessaoRequestDTO.getIdSessaoVotacao()) == null));
    }

    private boolean validaCpf(String cpf) {
        try {
//            final var s = validaCpfService.validaCpf(cpf);
//            if (s.equals(StatusCpfEnum.ABLE_TO_VOTE))
            return true;
        } catch (FeignException e) {
            e.printStackTrace();
        }
        return false;
    }

}
