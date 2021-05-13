package de.rvneptun.service;

import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.dto.TokenAndPassword;
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

import javax.servlet.http.HttpServletRequest;

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

    public MitgliedDto findByUsername(String username)
    {

        return mitgliedMapper.map(mitgliedRepository
                .findByUsername(username)
                .orElseThrow(() -> new MitgliedNotFoundException(username))
        );
    }

    public void sendMail(Mitglied mitglied, HttpServletRequest request)
    {

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(mitglied.getUsername());
        msg.setFrom(mailabsender);
        msg.setSubject("Registrieren: " + mitglied.getVorname() + "  " + mitglied.getName());
        msg.setText(getHttpProtocol(request) + "/mitglieder/registrieren/setpassword/" + mitglied.getRegistertoken());

        javaMailSender.send(msg);
    }

    private String getHttpProtocol(HttpServletRequest request)
    {
        return "http" + (request.isSecure() ? "s" : "") + "://"
               + request.getServerName()
               + (request.getServerPort() != 80 ? ":" + request.getServerPort() : "");
    }

    @Transactional
    public void register(MitgliedDto mitgliedDto, HttpServletRequest request)
    {
        Mitglied mitglied = mitgliedMapper.map(mitgliedDto);

        if (mitgliedRepository.existsMitgliedByUsername(mitglied.getUsername())) {
            throw new RuntimeException("Die E-Mailadresse ist bereits registriert");
        }

        mitglied.setRegistertoken(TokenUtils.getRandomToken(64));
        mitgliedRepository.save(mitglied);

        sendMail(mitglied, request);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public String registerToken(TokenAndPassword tokenAndPassword)
    {
        Mitglied mitglied = mitgliedRepository
                .findOneByRegistertoken(tokenAndPassword.getToken())
                .orElseThrow(() -> new RuntimeException("Der Token ist ungültig"));

        mitglied.setPassword(passwordEncoder.encode(tokenAndPassword.getPassword()));
        mitglied.setRegistertoken(null);

        return "redirect:/login";
    }

}
