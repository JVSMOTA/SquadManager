package br.com.SquadManager.dtos;

import br.com.SquadManager.models.ColaboradorModel;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record SquadRecordDto(
        @NotBlank
        String nome,
        List<ColaboradorModel> colaboradores
    ) {
}
