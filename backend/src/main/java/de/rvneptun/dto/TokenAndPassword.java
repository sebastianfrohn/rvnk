package de.rvneptun.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
public final class TokenAndPassword {

    @Getter
    private String token;

    private String password1;

    private String password2;

    public String getPassword()
    {
        if (password1 == null || password1.isEmpty() || !password1.equals(password2)) {
            throw new RuntimeException("Die Passwörter sind ungeeignet oder stimmen nicht überein");
        }
        return password1;
    }

}
