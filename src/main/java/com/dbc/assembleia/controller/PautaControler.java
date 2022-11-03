package com.dbc.assembleia.controller;


import com.dbc.assembleia.controller.doc.PautaControlerDoc;
import com.dbc.assembleia.dto.PautaDTO;
import com.dbc.assembleia.models.Pauta;
import com.dbc.assembleia.service.PautaService;
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
import java.util.Optional;

@Controller
@RequestMapping("/dbc/assembleia")
@AllArgsConstructor
public class PautaControler implements PautaControlerDoc {

    private final PautaService pautaService;

    @PostMapping("/pauta")
    public ResponseEntity<Void> novaPauta(@RequestBody PautaDTO pautaDto) {
        pautaService.save(pautaDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pautas")
    public ResponseEntity<List<Pauta>> getPautas() {
        var pautas = pautaService.getPautas();
        return new ResponseEntity<>(pautas, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/pauta/{id}")
    public ResponseEntity<PautaDTO> Pautas(@PathVariable long id) {
        var pautaDTO = pautaService.getPautaId(id);
        return new ResponseEntity<>(pautaDTO, new HttpHeaders(), HttpStatus.OK);
    }

}
