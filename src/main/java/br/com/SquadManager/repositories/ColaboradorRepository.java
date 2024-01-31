package br.com.SquadManager.repositories;

import br.com.SquadManager.models.ColaboradorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ColaboradorRepository extends JpaRepository<ColaboradorModel, UUID> {
}