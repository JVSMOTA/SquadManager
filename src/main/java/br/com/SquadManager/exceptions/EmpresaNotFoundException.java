package br.com.SquadManager.exceptions;

public class EmpresaNotFoundException extends SquadManagerException {
    public EmpresaNotFoundException() {
        super("Empresa not found.");
    }
}
