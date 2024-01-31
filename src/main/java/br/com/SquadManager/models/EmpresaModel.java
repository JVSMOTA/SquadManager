package br.com.SquadManager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_EMPRESAS",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_nome", columnNames = "nome")
        }
)

public class EmpresaModel extends RepresentationModel<EmpresaModel> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idEmpresa;
    private String nome;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SquadModel> squads;

}
