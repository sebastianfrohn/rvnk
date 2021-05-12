package de.rvneptun.service;

import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.entity.Mitglied;
import de.rvneptun.entity.Rolle;
import de.rvneptun.exception.MitgliedNotFoundException;
import de.rvneptun.mapper.MitgliedMapper;
import de.rvneptun.misc.UserHelper;
import de.rvneptun.repository.MitgliedRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MitgliedService {

    private final MitgliedRepository mitgliedRepository;
    private final MitgliedMapper mitgliedMapper;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    @Value("${rvnk.mail.from}")
    private String mailabsender;

    @Value("${rvnk.host}")
    private String host;

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
        mitglied.setUsername(newMitglied.getUsername());

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
                .orElseThrow(() -> new MitgliedNotFoundException(username))
        );
    }

    public String encode(String pwd) {
        return passwordEncoder.encode(pwd);
    }

    public void sendMail(Mitglied mitglied) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mitglied.getUsername());
        msg.setFrom(mailabsender);
        msg.setSubject("Registrieren: " + mitglied.getVorname() + "  " + mitglied.getName());
        msg.setText("http://" + host + "/mitglieder/registrieren/setpassword/" + mitglied.getRegistertoken());

        javaMailSender.send(msg);
    }

    @Transactional
    public void register(MitgliedDto mitgliedDto) {
        Mitglied mitglied = mitgliedMapper.map(mitgliedDto);
        UUID uuid = UUID.randomUUID();
        mitglied.setRegistertoken(uuid.toString());
        mitgliedRepository.save(mitglied);
        sendMail(mitglied);
    }

    @Transactional
    public String registerToken(String token, String password1, String password2) {
        if (!password1.equals(password2)) {
            return "redirect:/mitglieder/setpassword";
        }
        Mitglied mitglied = mitgliedRepository.findOneByRegistertoken(token).orElseThrow(() -> new MitgliedNotFoundException(0));
        mitglied.setPassword(passwordEncoder.encode(password1));
        mitglied.setRegistertoken(null);
        return "redirect:/";
    }
}
