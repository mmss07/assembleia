package com.dbc.assembleia.controller;


import com.dbc.assembleia.dto.PautaRequestDTO;
import com.dbc.assembleia.dto.PautaResponseDTO;
import com.dbc.assembleia.models.Pauta;
import com.dbc.assembleia.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dbc/assembleia")
@AllArgsConstructor
@Api(value = " Módulo de Pauta")
public class PautaControler {

    private final PautaService pautaService;

    @PostMapping("/pauta")
    @ApiOperation(value = "Salva uma nova Pauta")
    public ResponseEntity<Void> savePauta(@RequestBody PautaRequestDTO pautaDto) {
        pautaService.save(pautaDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pautas")
    @ApiOperation(value = "Retorna todas as Pautas")
    public ResponseEntity<List<PautaResponseDTO>> getPautas() {
        var pautas = pautaService.getPautas();
        return new ResponseEntity<>(pautas, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/pauta/{id}")
    @ApiOperation(value = "Retorna uma Pauta única")
    public ResponseEntity<PautaResponseDTO> getPauta(@PathVariable long id) {
        var pautaDTO = pautaService.getPautaResponseDTO(id);
        return new ResponseEntity<>(pautaDTO, new HttpHeaders(), HttpStatus.OK);
    }

}
