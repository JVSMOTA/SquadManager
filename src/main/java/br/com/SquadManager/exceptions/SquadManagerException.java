package br.com.SquadManager.exceptions;

public class SquadManagerException extends RuntimeException {
    public SquadManagerException() {
        super("Erro inesperado no SquadManager!");
    }

    public SquadManagerException(String message) {
        super(message);
    }
}
