package de.rvneptun.rest.controller;

import de.rvneptun.dto.TerminDto;
import de.rvneptun.service.TerminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.rvneptun.misc.UserHelper.currentUserDetails;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Termin")
public class TerminController {

    private final TerminService TerminService;

    @GetMapping("/list")
    public List<TerminDto> listAvailable() {
        log.info(currentUserDetails().map(UserDetails::getUsername).orElse("Es ist kein Nutzer angemeldet :-("));

        return TerminService.findAll();
    }

    @GetMapping("/{id}")
    @Transactional
    public void  find(@PathVariable Long id) {
        TerminService.find(id);
    }

    @PostMapping("/")
    @Transactional
    public Long addElement(@RequestBody TerminDto element) {
        return TerminService.add(element);
    }

    @PutMapping("/{id}")
    @Transactional
    public Long updateElement(@PathVariable Long id, @RequestBody TerminDto element) {
        return TerminService.update(id, element);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void  updateElement(@PathVariable Long id) {
        TerminService.delete(id);
    }


}
