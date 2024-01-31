package br.com.SquadManager.models;

public enum Cargo {
    PO("product owner"),
    SCRUM("scrum master"),
    DESIGNERUX("designer ux"),
    QA("quality assurance"),
    DEVFRONT("dev frontend"),
    DEVBACK("dev backend");

    private final String cargo;

    Cargo(String cargo){
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

}
