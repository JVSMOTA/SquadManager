package br.com.SquadManager.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "TB_COLABORADORES",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_email", columnNames = "email")
        }
)
public class ColaboradorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idColaborador;
    private String nome;
    private Cargo cargo;
    private String email;

}
