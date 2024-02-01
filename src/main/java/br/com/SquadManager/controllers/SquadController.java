package br.com.SquadManager.controllers;

import br.com.SquadManager.dtos.SquadRecordDto;
import br.com.SquadManager.exceptions.SquadNotFoundException;
import br.com.SquadManager.models.ColaboradorModel;
import br.com.SquadManager.models.SquadModel;
import br.com.SquadManager.services.ColaboradorService;
import br.com.SquadManager.services.SquadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/squads")
public class SquadController {

    @Autowired
    SquadService squadService;

    @Autowired
    ColaboradorService colaboradorService;

    @PostMapping
    public ResponseEntity<?> createSquad(@Valid @RequestBody SquadRecordDto squadRecordDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(squadService.createSquad(squadRecordDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{idSquad}/adicionar-colaborador")
    public ResponseEntity<Object> adicionarColaboradorAoSquad(
            @PathVariable(value = "idSquad") UUID idSquad,
            @RequestBody Long idColaborador) {
        try {
            SquadModel squadAtualizado = squadService.adicionarColaboradorAoSquad(idSquad, idColaborador);
            return ResponseEntity.status(HttpStatus.OK).body(squadAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{idSquad}")
    public ResponseEntity<Object> getOneSquad(@PathVariable(value = "idSquad") UUID idSquad) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(squadService.getSquadById(idSquad));
        } catch (SquadNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<SquadModel>> getAllSquads() {
        return ResponseEntity.status(HttpStatus.OK).body(squadService.getAllSquads());
    }

    @GetMapping("/{idSquad}/colaboradores")
    public ResponseEntity<List<ColaboradorModel>> listarColaboradoresDaSquad(
            @PathVariable(value = "idSquad") UUID idSquad) {
        return ResponseEntity.status(HttpStatus.OK).body(squadService.listarColaboradoresDaSquad(idSquad));
    }

    @PutMapping("/{idSquad}")
    public ResponseEntity<Object> updateSquad(@PathVariable(value = "idSquad") UUID idSquad, @Valid @RequestBody SquadRecordDto squadRecordDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(squadService.updateSquad(idSquad, squadRecordDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{idSquad}/colaboradores/{idColaborador}")
    public ResponseEntity<?> removerColaboradorDeSquad(
            @PathVariable(value = "idSquad") UUID idSquad,
            @PathVariable(value = "idColaborador") Long idColaborador) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(squadService.removerColaboradorDeSquad(idSquad, idColaborador));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{idSquad}")
    public ResponseEntity<?> deleteSquad(@PathVariable(value = "idSquad") UUID idSquad) {
        try {
            squadService.deleteSquad(idSquad);
            return ResponseEntity.status(HttpStatus.OK).body("Squad deleted successfully.");
        } catch (SquadNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}