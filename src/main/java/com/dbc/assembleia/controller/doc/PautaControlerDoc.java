package com.dbc.assembleia.controller.doc;

import com.dbc.assembleia.dto.PautaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface PautaControlerDoc {

    @Operation(description = "Casdastra uma nova Pauta.")
    @ApiResponse(
            responseCode = "200",
            description = "Pauta cadastrada com sucesso.")
    @ApiResponse(
            responseCode = "400",
            description = "Erro de requisição.")
    ResponseEntity<Void> novaPauta(@RequestBody PautaDTO pautaDTO);

}
