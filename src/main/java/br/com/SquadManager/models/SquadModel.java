package br.com.SquadManager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_SQUAD")
public class SquadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idSquad;
    private String nome;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idEmpresa")
    private EmpresaModel empresa;

    @OneToMany(mappedBy = "squad", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ColaboradorModel> colaboradores;

}
