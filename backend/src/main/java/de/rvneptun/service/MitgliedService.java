package de.rvneptun.service;

import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.entity.Mitglied;
import de.rvneptun.exception.MitgliedNotFoundException;
import de.rvneptun.mapper.MitgliedMapper;
import de.rvneptun.misc.Rolle;
import de.rvneptun.misc.UserHelper;
import de.rvneptun.repository.MitgliedRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MitgliedService {

    private final MitgliedRepository mitgliedRepository;
    private final MitgliedMapper mitgliedMapper;
    private final PasswordEncoder passwordEncoder;

    public List<MitgliedDto> findAll() {
        return mitgliedMapper.map(mitgliedRepository.findAll());
    }

    @Transactional
    public Long add(MitgliedDto element) {
        return mitgliedRepository.save(mitgliedMapper.map(element)).getId();
    }

    @Transactional
    public Long update(Long id, MitgliedDto mitgliedDto) {
        Mitglied mitglied = mitgliedRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("konnte keine Entity mit ID " + id + "finden"));

        Mitglied newMitglied = mitgliedMapper.map(mitgliedDto);

        mitglied.setName(newMitglied.getName());
        mitglied.setVorname(newMitglied.getVorname());
        mitglied.setEmail(newMitglied.getEmail());

        List<Rolle> rollen = newMitglied.getRollen();
        rollen.remove(Rolle.SUPERADMIN);

        mitglied.setRollen(rollen);

        mitgliedRepository.saveAndFlush(mitglied);

        if (UserHelper.getAngemeldetesMitglied().getRollen().contains(Rolle.SUPERADMIN) && !Strings.isEmpty(newMitglied.getPassword())) {
            mitglied.setPassword(passwordEncoder.encode(newMitglied.getPassword()));
        }

        return id;
    }

    @Transactional
    public void delete(Long id) {
        mitgliedRepository.deleteById(id);
    }

    public MitgliedDto findById(Long id) {
        return mitgliedMapper.map(mitgliedRepository
                .findById(id)
                .orElseThrow(() ->  new MitgliedNotFoundException(id))
        );
    }

    public MitgliedDto findByUsername(String username) {
        return mitgliedMapper.map(mitgliedRepository
                .findByUsername(username)
                .orElseThrow(() -> new MitgliedNotFoundException(username) )
        );
    }

    public String encode(String pwd) {
        return passwordEncoder.encode(pwd);
    }
}
