package com.dbc.assembleia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PautaDTO {


    @ApiModelProperty(notes = "Identificador da pauta exclusivo para garantir que as pautas sejam cadastradas apenas uma vez.",
            example = "1")
    @JsonProperty("id_pauta")
    private Long idPauta;

    @ApiModelProperty(notes = "Nome da Pauta", example = "Compra de novo arcondicionado")
    @JsonProperty("nome")
    private String nome;

    @ApiModelProperty(notes = "Descricao da Pauta", example = "Compra de novo arcondicionado no dia xx no valor xxx")
    @JsonProperty("descricao")
    private String descricao;

    @ApiModelProperty(notes = "Data em que aconteceu a Pauta", example = "dd/mm/AAAA")
    @JsonProperty("dataPauta")
    private LocalDate dataPauta;

}
