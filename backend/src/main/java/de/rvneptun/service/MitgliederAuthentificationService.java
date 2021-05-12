package de.rvneptun.service;

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
        return mitgliedService.findByUsername(username);
    }

}
