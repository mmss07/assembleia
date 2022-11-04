package com.dbc.assembleia.repository;

import com.dbc.assembleia.models.Pauta;
import com.dbc.assembleia.models.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

    List<SessaoVotacao> findByPauta(Pauta pauta);
}
