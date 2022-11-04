package com.dbc.assembleia.dto;

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
public class SessaoVotacaoResponseDTO {

    @ApiModelProperty(notes = "Id da Pauta",
            example = "Pauta.class")
    @JsonProperty("id_pauta")
    private Long idPauta;

    @ApiModelProperty(notes = "Tempo da sessão de votação informado na abertura da mesma! Caso não tenha sido informado será terá o valor default de 1 minuto!",
            example = "1")
    @JsonProperty("tempo")
    private Long tempo;

    @ApiModelProperty(notes = "Tempo da sessão de votação informado na abertura da mesma! Caso não tenha sido informado será terá o valor default de 1 minuto!",
            example = "1")
    @JsonProperty("id_sessao_votacao")
    private Long idSessaoVotacao;


    @ApiModelProperty(notes = "Tempo da sessão de votação informado na abertura da mesma! Caso não tenha sido informado será terá o valor default de 1 minuto!",
            example = "1")
    @JsonProperty("data_hora_abertura")
    private String dataHoraAbertura;

    @ApiModelProperty(notes = "Tempo da sessão de votação informado na abertura da mesma! Caso não tenha sido informado será terá o valor default de 1 minuto!",
            example = "1")
    @JsonProperty("data_hora_fechamento")
    private String dataHoraFechamento;

    @ApiModelProperty(notes = "Total de votos recebidos - SIM",
            example = "1")
    @JsonProperty("total_votos_sim")
    private long totalVotosSim;

    @ApiModelProperty(notes = "Total de votos recebidos - NÃO",
            example = "1")
    @JsonProperty("total_votos_nao")
    private long totalVotosNao;


}
