package com.dbc.assembleia.dto;

import com.dbc.assembleia.models.Pauta;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessaoVotacaoRequestDTO {

    @ApiModelProperty(notes = "Id da Pauta",
            example = "Pauta.class")
    @JsonProperty("id_pauta")
    private Long idPauta;

    @ApiModelProperty(notes = "Tempo da sessão de votação informado na abertura da mesma! Caso não tenha sido informado será terá o valor default de 1 minuto!",
            example = "1")
    @JsonProperty("tempo")
    private Long tempo;

}
