package com.dbc.assembleia.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "voto_sessao")
public class VotoSessao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVotoSessao;

    private Long idSessaoVotacao;

    private String cpf;

    private String voto;


}
