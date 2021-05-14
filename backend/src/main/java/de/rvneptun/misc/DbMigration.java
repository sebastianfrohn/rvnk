package de.rvneptun.misc;

import de.rvneptun.entity.DbScheme;
import de.rvneptun.entity.Mitglied;
import de.rvneptun.enums.Rolle;
import de.rvneptun.repository.DbSchemeRepository;
import de.rvneptun.repository.MitgliedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DbMigration {

    private final MitgliedRepository mitgliedRepository;

    private final DbSchemeRepository dbSchemeRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    private void scheme_1(List<Long> schemes)
    {
        if (!schemes.contains(1L)) {
            List<Rolle> rollen = new ArrayList<>();
            rollen.add(Rolle.ADMIN);
            rollen.add(Rolle.ORGANISATOR);

            Mitglied mitglied = Mitglied.builder()
                    .name("Admin")
                    .username("admin@example.com")
                    .password(passwordEncoder.encode("admin"))
                    .vorname("Admin")
                    .rollen(rollen)
                    .build();

            mitgliedRepository.save(mitglied);

            dbSchemeRepository.save(DbScheme.builder().id(1L).build());

            log.info("Initialen Benutzer 'admin@example.com' mit Passwort 'admin' erstellt");
        }
    }

    @PostConstruct
    public void migrate()
    {
        List<Long> schemes = dbSchemeRepository.findAllIds();

        scheme_1(schemes);
    }

}
