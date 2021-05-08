package de.rvneptun.misc;

import de.rvneptun.dto.MitgliedDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class UserHelper {

    public static MitgliedDto getROLE_ANGEMELDETesMitglied() {
        Object mitglied = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .orElseThrow(() -> new RuntimeException("Ein seltsamer Fehler ist aufgetreten... kein Kontext"));
        if (mitglied instanceof String) {
            return null;
        }
        return (MitgliedDto) mitglied;
    }

}
