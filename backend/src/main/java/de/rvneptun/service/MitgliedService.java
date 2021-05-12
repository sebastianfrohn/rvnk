package de.rvneptun.service;

import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.entity.Mitglied;
import de.rvneptun.exception.MitgliedNotFoundException;
import de.rvneptun.mapper.MitgliedMapper;
import de.rvneptun.misc.TokenUtils;
import de.rvneptun.repository.MitgliedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public MitgliedDto findByUsername(String username) {
        return mitgliedMapper.map(mitgliedRepository
                .findByUsername(username)
                .orElseThrow(() -> new MitgliedNotFoundException(username))
        );
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
        String token = TokenUtils.getRandomToken(64);

        Mitglied mitglied = mitgliedMapper.map(mitgliedDto);
        mitglied.setRegistertoken(token);

        mitgliedRepository.save(mitglied);

        sendMail(mitglied);
    }

    @Transactional
    public String registerToken(String token, String password1, String password2) {

        if (!password1.equals(password2)) {
            return "redirect:/mitglieder/setpassword";
        }

        Mitglied mitglied = mitgliedRepository.findOneByRegistertoken(token)
                .orElseThrow(() -> new RuntimeException("Der Token ist ungültig"));

        mitglied.setPassword(passwordEncoder.encode(password1));
        mitglied.setRegistertoken(null);

        return "redirect:/";
    }

}
