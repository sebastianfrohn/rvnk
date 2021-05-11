package de.rvneptun.misc.exception;

public class TerminNotFoundException extends RuntimeException {

    private Long id;

    public TerminNotFoundException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Der Arbeitseinsatz mit der ID " + id + " existiert nicht";
    }

}
