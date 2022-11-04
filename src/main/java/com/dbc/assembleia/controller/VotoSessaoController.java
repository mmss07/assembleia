package com.dbc.assembleia.controller;

import com.dbc.assembleia.dto.VotoSessaoRequestDTO;
import com.dbc.assembleia.service.VotoSessaoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/dbc/assembleia")
public class VotoSessaoController {

    private final VotoSessaoService votoSessaoService;

    @PostMapping("/voto")
    @ApiOperation(value = "Salva um voto para sessão que foi enviada")
    public ResponseEntity<Void> save(@RequestBody VotoSessaoRequestDTO voto) {
        var votoSessaoResponseDTO = votoSessaoService.save(voto);
        if (votoSessaoResponseDTO.getIdVotoSessao() != null)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
