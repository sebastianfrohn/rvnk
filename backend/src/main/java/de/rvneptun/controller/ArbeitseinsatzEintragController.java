package de.rvneptun.controller;

import de.rvneptun.dto.ArbeitseinsatzDto;
import de.rvneptun.dto.ArbeitseinsatzEintragDto;
import de.rvneptun.service.ArbeitseinsatzEintragService;
import de.rvneptun.service.ArbeitseinsatzService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/arbeitseinsatz/eintrag")
public class ArbeitseinsatzEintragController {

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
