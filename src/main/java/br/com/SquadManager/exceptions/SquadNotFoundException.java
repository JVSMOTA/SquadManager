package br.com.SquadManager.exceptions;

public class SquadNotFoundException extends SquadManagerException {
    public SquadNotFoundException() {
        super("Squad not found.");
    }
}
