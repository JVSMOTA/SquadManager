package br.com.SquadManager.exceptions;

public class ColaboradorNotFoundException extends SquadManagerException {
    public ColaboradorNotFoundException() {
        super("Colaborador not found.");
    }
}
