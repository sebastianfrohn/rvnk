package de.rvneptun.controller.web;


import de.rvneptun.controller.dto.TerminDto;
import de.rvneptun.data.mapper.TerminVoMapper;
import de.rvneptun.service.TerminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/termine")
@RequiredArgsConstructor
public class TerminController extends DefaultController {

    private final TerminVoMapper terminVoMapper;

    private final TerminService terminService;

    @GetMapping("/")
    public String overwiew(Model model) {
        List<TerminDto> termine = terminService.findAll();
        model.addAttribute("termine", terminVoMapper.map(termine));
        return "termine/list";
    }


}