package br.com.SquadManager.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import org.apache.logging.log4j.message.Message;
import org.aspectj.lang.annotation.DeclareError;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.UUID;

@Data
@Entity
@Table(name = "TB_EMPRESAS",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_nome", columnNames = "nome")
        }
)

public class EmpresaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idEmpresa;
    private String nome;

}
