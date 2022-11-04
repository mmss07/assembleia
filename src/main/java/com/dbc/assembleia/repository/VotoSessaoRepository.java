package com.dbc.assembleia.repository;

import com.dbc.assembleia.models.VotoSessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoSessaoRepository extends JpaRepository<VotoSessao, Long> {


   List<VotoSessao> findByIdSessaoVotacao(Long idSessaoVotacao);
   VotoSessao findByCpfAndIdSessaoVotacao(String cpf, Long idSessaoVotacao);

}
