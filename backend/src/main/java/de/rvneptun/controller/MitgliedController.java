package de.rvneptun.controller;

import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.service.MitgliedService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("mitglieder")
@RequiredArgsConstructor
public class MitgliedController extends DefaultController {

    // das sollte man noch ändern, geht sicher eleganter...
    @Data
    public final class TokenAndPassword {
        private String token;
        private String password1;
        private String password2;
    }

    private final MitgliedService mitgliedService;

    @GetMapping("/benutzerdaten")
    public String hello(Model model) {
        return "mitglieder/benutzerdaten";
    }

    @GetMapping("/registrieren")
    public String registriereen(Model model) {
        model.addAttribute("mitglied", new MitgliedDto());
        return "mitglieder/registrieren";
    }

    @PostMapping("/registrieren")
    public String registriereen(@ModelAttribute MitgliedDto mitglied, Model model) {
        mitgliedService.register(mitglied);
        return "redirect:/";
    }

    @GetMapping("/registrieren/setpassword/{token}")
    public String registrierenForm(@PathVariable String token, Model model) {
        model.addAttribute("token", token);

        return "mitglieder/setpassword";
    }

    @PostMapping("/registrieren/setpassword")
    public String registrierenSave(@ModelAttribute TokenAndPassword tokenAndPassword, Model model) {
        model.addAttribute("token", tokenAndPassword.getToken());
        return mitgliedService.registerToken(tokenAndPassword.getToken(), tokenAndPassword.getPassword1(), tokenAndPassword.getPassword2());
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

}