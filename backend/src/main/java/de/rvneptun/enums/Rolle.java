package de.rvneptun.enums;

import org.springframework.security.core.GrantedAuthority;

public enum
Rolle implements GrantedAuthority {
    ANGEMELDET,
    MITGLIED,
    ORGANISATOR,
    ADMIN,
    SUPERADMIN;

    @Override
    public String getAuthority()
    {
        return this.name();
    }
}
