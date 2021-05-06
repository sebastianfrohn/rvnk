package de.rvneptun.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.rvneptun.misc.Role;
import lombok.*;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MitgliedDto implements UserDetails {

    private Long id;

    private String username;

    private String vorname;

    private String name;

    @JsonIgnore
    private String password;

    private String email;

    private List<Role> roles;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
