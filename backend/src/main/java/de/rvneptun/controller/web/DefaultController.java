package de.rvneptun.controller.web;

import de.rvneptun.controller.dto.MitgliedDto;
import org.springframework.web.bind.annotation.ModelAttribute;

import static de.rvneptun.misc.UserHelper.getAngemeldetesMitglied;

public abstract class DefaultController {

    @ModelAttribute("user")
    public MitgliedDto user() {
        return  getAngemeldetesMitglied();
    }
}
