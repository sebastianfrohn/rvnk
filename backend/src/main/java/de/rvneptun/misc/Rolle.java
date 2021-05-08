package de.rvneptun.misc;

import org.springframework.security.core.GrantedAuthority;

public enum Rolle implements GrantedAuthority {
    MITGLIED,
    ADMIN,
    ORGANISATOR,
    SUPERADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
