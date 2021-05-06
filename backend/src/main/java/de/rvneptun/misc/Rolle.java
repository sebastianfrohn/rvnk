package de.rvneptun.misc;

import org.springframework.security.core.GrantedAuthority;

public enum Rolle implements GrantedAuthority {
    MITGLIED,
    ADMIN,
    ORGANISATOR;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
