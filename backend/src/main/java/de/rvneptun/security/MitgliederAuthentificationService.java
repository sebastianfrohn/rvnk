package de.rvneptun.security;

import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.service.MitgliedService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MitgliederAuthentificationService implements UserDetailsService {

    private final MitgliedService mitgliedService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MitgliedDto user = mitgliedService.findByUsername(username);


        return null;
    }
}
