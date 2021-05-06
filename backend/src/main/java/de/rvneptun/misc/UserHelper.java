package de.rvneptun.misc;

import de.rvneptun.dto.MitgliedDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class UserHelper {

    public static MitgliedDto getAngemeldetesMitglied() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map( m -> (MitgliedDto)m)
                .orElseThrow(() ->    new RuntimeException("Nicht angemeldet!"));
    }

}
