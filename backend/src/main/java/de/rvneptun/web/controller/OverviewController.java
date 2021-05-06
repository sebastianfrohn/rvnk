package de.rvneptun.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static de.rvneptun.misc.UserHelper.getAngemeldetesMitglied;

@Controller
public class OverviewController {

    @GetMapping("/")
    public String overwiew(Model model) {
        return "home";
    }

    @GetMapping("/hello")
    public String hello(Model model) {

        model.addAttribute("name", getAngemeldetesMitglied().getUsername());

        return "hello";
    }

}