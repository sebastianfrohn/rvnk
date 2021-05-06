package de.rvneptun.exception;

public class ArbeitseinsatzException extends  RuntimeException{

    private Long id;

    public ArbeitseinsatzException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return  "Der Arbeitseinsatz mit der ID " + id + " existiert nicht";
    }

}
