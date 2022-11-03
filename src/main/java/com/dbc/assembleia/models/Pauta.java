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
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPauta;
    private String nome;
    private String descricao;
    private LocalDate dataPauta;


}
