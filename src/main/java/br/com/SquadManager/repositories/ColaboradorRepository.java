package br.com.SquadManager.repositories;

import br.com.SquadManager.models.ColaboradorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorModel, Long> {
    ColaboradorModel findByNome(String nome);
}