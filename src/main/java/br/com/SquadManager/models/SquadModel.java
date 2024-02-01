package br.com.SquadManager.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_SQUADS")
public class SquadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idSquad;
    private String nome;

    @OneToMany(mappedBy = "squad")
    private List<ColaboradorModel> colaboradores;

}
