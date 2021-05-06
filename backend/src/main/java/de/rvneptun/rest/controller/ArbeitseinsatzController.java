package de.rvneptun.rest.controller;

import de.rvneptun.dto.ArbeitseinsatzDto;
import de.rvneptun.service.ArbeitseinsatzService;
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
@RequestMapping("/api/arbeitseinsatz")
public class ArbeitseinsatzController {

    private final ArbeitseinsatzService arbeitseinsatzService;

    @GetMapping("/list")
    public List<ArbeitseinsatzDto> listAvailable() {
        log.info(currentUserDetails().map(UserDetails::getUsername).orElse("Es ist kein Nutzer angemeldet :-("));

        return arbeitseinsatzService.findAll();
    }

    @GetMapping("/{id}")
    @Transactional
    public void  find(@PathVariable Long id) {
        arbeitseinsatzService.find(id);
    }

    @PostMapping("/")
    @Transactional
    public Long addElement(@RequestBody ArbeitseinsatzDto element) {
        return arbeitseinsatzService.add(element);
    }

    @PutMapping("/{id}")
    @Transactional
    public Long updateElement(@PathVariable Long id, @RequestBody ArbeitseinsatzDto element) {
        return arbeitseinsatzService.update(id, element);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void  updateElement(@PathVariable Long id) {
        arbeitseinsatzService.delete(id);
    }


}
