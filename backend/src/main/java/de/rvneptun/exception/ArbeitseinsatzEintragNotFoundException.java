package de.rvneptun.exception;

public class ArbeitseinsatzEintragNotFoundException extends RuntimeException {

    private Long id;

    public ArbeitseinsatzEintragNotFoundException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Der Arbeitseinsatz-Eintrag mit der ID " + id + " existiert nicht";
    }

}
