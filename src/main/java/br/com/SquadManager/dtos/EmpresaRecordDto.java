package br.com.SquadManager.dtos;

import jakarta.validation.constraints.NotBlank;

public record EmpresaRecordDto(@NotBlank String nome) {
}
