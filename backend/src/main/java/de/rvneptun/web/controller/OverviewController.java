package de.rvneptun.web.controller;


import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.misc.WithUserMetadata;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static de.rvneptun.misc.UserHelper.getAngemeldetesMitglied;

@Controller
public class OverviewController extends DefaultController {

    @GetMapping("/")
    public String overwiew(Model model) {
        return "home";
    }

    @Secured("ROLE_MITGLIED")
    @GetMapping("/hello")
    public String hello(Model model) {
        return "hello";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

}