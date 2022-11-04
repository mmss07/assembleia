package com.dbc.assembleia.service;

import com.dbc.assembleia.dto.PautaRequestDTO;
import com.dbc.assembleia.dto.PautaResponseDTO;
import com.dbc.assembleia.models.Pauta;
import com.dbc.assembleia.repository.PautaRepository;
import com.dbc.assembleia.repository.SessaoVotacaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;
    private final ModelMapper modelMapper;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    public void save(PautaRequestDTO pautaDTO) {

        var pauta = modelMapper.map(pautaDTO, Pauta.class);
        pauta.setDataPauta(LocalDate.now());
        pautaRepository.save(pauta);

    }

    public List<PautaResponseDTO> getPautas() {
        final var pautas = pautaRepository.findAll().stream().map(x -> {
            x.setListSessao(sessaoVotacaoRepository.findByPauta(x));
            return modelMapper.map(x, PautaResponseDTO.class);
        }).collect(Collectors.toList());
        return pautas;
    }

    public PautaResponseDTO getPautaResponseDTO(Long id) {
        var byId = pautaRepository.findById(id);
        var pautaDTO = PautaResponseDTO.builder().build();
        if (byId.isPresent())
            pautaDTO = modelMapper.map(byId.get(), PautaResponseDTO.class);
        return pautaDTO;
    }

    public Pauta getPautaById(Long id) {
        final var pauta = pautaRepository.findById(id);
        return pauta.orElse(null);
    }

}
