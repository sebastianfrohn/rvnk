package de.rvneptun.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.rvneptun.misc.Rolle;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
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

    private List<Rolle> rolles;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolles;
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
