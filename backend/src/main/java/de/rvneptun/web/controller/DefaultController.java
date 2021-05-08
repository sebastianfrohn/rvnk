package de.rvneptun.web.controller;

import de.rvneptun.dto.MitgliedDto;
import org.springframework.web.bind.annotation.ModelAttribute;

import static de.rvneptun.misc.UserHelper.getROLE_ANGEMELDETesMitglied;

public abstract class DefaultController {

    @ModelAttribute("user")
    public MitgliedDto user() {
        return  getROLE_ANGEMELDETesMitglied();
    }
}
