package com.dbc.assembleia.service;

import com.dbc.assembleia.dto.PautaDTO;
import com.dbc.assembleia.models.Pauta;
import com.dbc.assembleia.repository.PautaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;
    private final ModelMapper modelMapper;


    public void save(PautaDTO pautaDTO){

        var pauta = modelMapper.map(pautaDTO, Pauta.class);
        pauta.setDataPauta(LocalDate.now());
        pautaRepository.save(pauta) ;

    }

    public List<Pauta> getPautas(){
        return pautaRepository.findAll();
    }

    public PautaDTO getPautaId(Long id){
        var byId = pautaRepository.findById(id);
        var pautaDTO = PautaDTO.builder().build();
        if(byId.isPresent())
            pautaDTO = modelMapper.map(byId.get(), PautaDTO.class);
        return pautaDTO;
    }

}
