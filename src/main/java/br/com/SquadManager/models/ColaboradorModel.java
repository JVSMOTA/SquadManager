package br.com.SquadManager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Data
@Entity
@Table(name = "TB_COLABORADORES",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_email", columnNames = "email")
        }
)
public class ColaboradorModel extends RepresentationModel<ColaboradorModel> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idColaborador;
    private String nome;
    private Cargo cargo;
    private String email;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idSquad")
    private SquadModel squad;

}
