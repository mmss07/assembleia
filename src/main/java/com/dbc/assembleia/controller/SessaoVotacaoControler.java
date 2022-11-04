package com.dbc.assembleia.controller;


import com.dbc.assembleia.dto.SessaoVotacaoRequestDTO;
import com.dbc.assembleia.dto.SessaoVotacaoResponseDTO;
import com.dbc.assembleia.service.SessaoVotacaoService;
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
@Api(value = " Módulo de Sessão para votação")
public class SessaoVotacaoControler {

    private final SessaoVotacaoService sessaoVotacaoService;

    @PostMapping("/sessao")
    @ApiOperation(value = "Insere uma nova sessão de votação")
    public ResponseEntity<SessaoVotacaoResponseDTO> saveSessao(@RequestBody SessaoVotacaoRequestDTO SessaoVotacaoDTO) {
        final var sessao = sessaoVotacaoService.save(SessaoVotacaoDTO);
        return new ResponseEntity<>(sessao, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/sessoes")
    @ApiOperation(value = "Retorna todas as Sessões de votações")
    public ResponseEntity<List<SessaoVotacaoResponseDTO>> getAllSessoes() {
        var listSessoes = sessaoVotacaoService.getAllSessoes();
        return new ResponseEntity<>(listSessoes, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/sessao/{id}")
    @ApiOperation(value = "Retorna uma sessão de votação uníca")
    public ResponseEntity<SessaoVotacaoResponseDTO> getSessao(@PathVariable long id) {
        var sessaoId = sessaoVotacaoService.getSessaoVotacaoResponseDTO(id);
        return new ResponseEntity<>(sessaoId, new HttpHeaders(), HttpStatus.OK);
    }

}
