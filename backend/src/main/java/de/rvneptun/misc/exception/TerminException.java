package de.rvneptun.misc.exception;

public class TerminException extends  RuntimeException{

    private Long id;

    public TerminException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return  "Der Arbeitseinsatz mit der ID " + id + " existiert nicht";
    }

}
