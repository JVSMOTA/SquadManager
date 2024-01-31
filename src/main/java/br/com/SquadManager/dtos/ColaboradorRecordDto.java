package br.com.SquadManager.dtos;

import br.com.SquadManager.models.Cargo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ColaboradorRecordDto(
        @NotBlank
        String nome,
        @NotNull
        Cargo cargo,
        @Email
        @NotBlank
        String email
    ) {
}
