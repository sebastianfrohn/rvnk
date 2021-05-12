package de.rvneptun.controller.rest;

import de.rvneptun.dto.ArbeitseinsatzEintragDto;
import de.rvneptun.service.ArbeitseinsatzEintragService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/arbeitseinsatz/eintrag")
public class ArbeitseinsatzEintragRestController {

    private final ArbeitseinsatzEintragService arbeitseinsatzEintragService;

    @GetMapping("/list")
    public List<ArbeitseinsatzEintragDto> listAvailable(@RequestParam(value = "name", defaultValue = "World") String name) {
        return arbeitseinsatzEintragService.findAll();
    }

    @GetMapping("/{id}")
    @Transactional
    public ArbeitseinsatzEintragDto find(@PathVariable Long id) {
        return arbeitseinsatzEintragService.find(id);
    }

    @PostMapping("/")
    @Transactional
    public Long addElement(@RequestBody ArbeitseinsatzEintragDto element) {
        return arbeitseinsatzEintragService.add(element);
    }

    @PutMapping("/{id}")
    @Transactional
    public Long updateElement(@PathVariable Long id, @RequestBody ArbeitseinsatzEintragDto element) {
        return arbeitseinsatzEintragService.update(id, element);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteElement(@PathVariable Long id) {
        arbeitseinsatzEintragService.delete(id);
    }



}
