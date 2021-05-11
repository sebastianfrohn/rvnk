package de.rvneptun.controller.web;


import de.rvneptun.controller.dto.ArbeitseinsatzEintragDto;
import de.rvneptun.data.entity.ArbeitseinsatzEintrag;
import de.rvneptun.data.mapper.ArbeitseinsatzEintragMapper;
import de.rvneptun.service.ArbeitseinsatzEintragService;
import de.rvneptun.service.TerminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.rvneptun.misc.UserHelper.getAngemeldetesMitglied;

@Controller
@RequestMapping("/arbeitseinsaetze")
@RequiredArgsConstructor
public class ArbeitseinsatzController extends DefaultController {


    private final ArbeitseinsatzEintragMapper eintragMapper;
    private final ArbeitseinsatzEintragService service;
    private final TerminService terminService;

    @GetMapping("/eigene")
    public String zeigeEigeneEintraege(Model model) {
        List<ArbeitseinsatzEintrag> eintraege = service.findAllByArbeiterId(getAngemeldetesMitglied().getId());
        List<ArbeitseinsatzEintragDto> eintraegeDto = eintragMapper.mapReverse(eintraege);

        model.addAttribute("titel", "Eigene angemeldete Arbeitseinsätze");
        model.addAttribute("einsaetze", eintraegeDto);
        return "arbeitseinsaetze/list";
    }

    @GetMapping("/einreichungen")
    public String zeigeEingereichteEintraege(Model model) {
        List<ArbeitseinsatzEintrag> eintraege = service.findAllByOrganisatorId(getAngemeldetesMitglied().getId());
        List<ArbeitseinsatzEintragDto> eintraegeDto = eintragMapper.mapReverse(eintraege);

        model.addAttribute("titel", "Eingereichte Arbeitseinsätze");
        model.addAttribute("einsaetze", eintraegeDto);
        return "arbeitseinsaetze/list";
    }

    @GetMapping("/einreichungen/erstellen/termin/{id}")
    public String erstelleEintrag(@PathVariable long id, Model model) {
        model.addAttribute("einsatz", terminService.getForTermin(id));
        return "arbeitseinsaetze/bearbeiten";
    }

    @GetMapping("/einreichungen/{id}/bearbeiten")
    public String bearbeitenEintrag(@PathVariable long id, Model model) {
        model.addAttribute("einsatz", eintragMapper.map(service.findById(id)));
        return "arbeitseinsaetze/bearbeiten";
    }

    @PostMapping("/einreichungen")
    public String save(@ModelAttribute ArbeitseinsatzEintragDto dto) {
        service.save(dto);
        return "redirect:/arbeitseinsaetze/eigene";
    }

}