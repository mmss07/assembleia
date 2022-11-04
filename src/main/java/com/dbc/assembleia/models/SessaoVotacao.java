package com.dbc.assembleia.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "sessao_votacao")
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSessaoVotacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    private LocalDateTime dataHoraAbertura;

    private LocalDateTime dataHoraFechamento;

    private Long tempo;


}
