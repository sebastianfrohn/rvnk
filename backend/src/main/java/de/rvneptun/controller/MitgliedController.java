package de.rvneptun.controller;

import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.dto.TokenAndPassword;
import de.rvneptun.service.MitgliedService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("mitglieder")
@RequiredArgsConstructor
public class MitgliedController extends DefaultController {

    private final MitgliedService mitgliedService;

    @GetMapping("/benutzerdaten")
    public String hello(Model model)
    {
        return "mitglieder/benutzerdaten";
    }

    @GetMapping("/registrieren")
    public String registriereen(Model model)
    {
        model.addAttribute("mitglied", new MitgliedDto());
        return "mitglieder/registrieren";
    }

    @PostMapping("/registrieren")
    public String registriereen(@ModelAttribute MitgliedDto mitglied, HttpServletRequest request, Model model)
    {
        try {
            mitgliedService.register(mitglied, request);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("mitglied", mitglied);
            return "mitglieder/registrieren";
        }
        return "redirect:/";
    }

    @GetMapping("/registrieren/setpassword/{token}")
    public String registrierenForm(@PathVariable String token, Model model)
    {
        model.addAttribute("token", token);

        return "mitglieder/setpassword";
    }

    @PostMapping("/registrieren/setpassword")
    public String registrierenSave(@ModelAttribute TokenAndPassword tokenAndPassword, RedirectAttributes attributes)
    {
        try {
            attributes.addAttribute("token", tokenAndPassword.getToken());
            return mitgliedService.registerToken(tokenAndPassword);
        } catch (RuntimeException e) {
            attributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/mitglieder/registrieren/setpassword/" + tokenAndPassword.getToken();
        }
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

}