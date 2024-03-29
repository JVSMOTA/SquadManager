package br.com.SquadManager.controllers;

import br.com.SquadManager.dtos.ColaboradorRecordDto;
import br.com.SquadManager.exceptions.ColaboradorNotFoundException;
import br.com.SquadManager.models.ColaboradorModel;
import br.com.SquadManager.services.ColaboradorService;
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

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    ColaboradorService colaboradorService;

    @PostMapping
    public ResponseEntity<?> createColaborador(@Valid @RequestBody ColaboradorRecordDto colaboradorRecordDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(colaboradorService.createColaborador(colaboradorRecordDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{idColaborador}")
    public ResponseEntity<Object> getOneColaborador(@PathVariable(value = "idColaborador") Long idColaborador) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.getColaboradorById(idColaborador));
        } catch (ColaboradorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ColaboradorModel>> getAllColaboradors() {
        return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.getAllColaboradores());
    }

    @PutMapping("/{idColaborador}")
    public ResponseEntity<Object> updateColaborador(@PathVariable(value = "idColaborador") Long idColaborador, @Valid @RequestBody ColaboradorRecordDto colaboradorRecordDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(colaboradorService.updateColaborador(idColaborador, colaboradorRecordDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{idColaborador}")
    public ResponseEntity<?> deleteColaborador(@PathVariable(value = "idColaborador") Long idColaborador) {
        try {
            colaboradorService.deleteColaborador(idColaborador);
            return ResponseEntity.status(HttpStatus.OK).body("Colaborador deleted successfully.");
        } catch (ColaboradorNotFoundException e) {
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