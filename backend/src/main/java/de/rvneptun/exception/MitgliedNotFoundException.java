package de.rvneptun.exception;

public class MitgliedNotFoundException extends  RuntimeException{

    private Object id;

    public MitgliedNotFoundException(Object id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return  "Das Mitglied mit der ID " + id.toString() + " existiert nicht";
    }

}
