package br.com.SquadManager.repositories;

import br.com.SquadManager.models.SquadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SquadRepository extends JpaRepository<SquadModel, UUID> {
}