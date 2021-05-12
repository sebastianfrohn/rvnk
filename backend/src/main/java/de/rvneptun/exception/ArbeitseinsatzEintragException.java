package de.rvneptun.exception;

public class ArbeitseinsatzEintragException extends  RuntimeException{

    private Long id;

    public ArbeitseinsatzEintragException(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return  "Der Arbeitseinsatz-Eintrag mit der ID " + id + " existiert nicht";
    }

}
