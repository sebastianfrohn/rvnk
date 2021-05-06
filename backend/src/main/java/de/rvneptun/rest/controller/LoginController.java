package de.rvneptun.rest.controller;

import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.service.MitgliedService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class LoginController {

    private final MitgliedService mitgliedService;

    @PostMapping("/login/{id}")
    public void login(@PathVariable Long id, @RequestBody String sessionId) {
        mitgliedService.login(id, sessionId);
    }

    @PostMapping("/logout/{id}")
    public void logout(@PathVariable Long id) {
        mitgliedService.logout(id);
    }
}
