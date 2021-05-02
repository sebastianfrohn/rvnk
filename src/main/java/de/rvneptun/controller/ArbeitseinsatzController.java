package de.rvneptun.controller;

import de.rvneptun.dto.ArbeitseinsatzDto;
import de.rvneptun.service.ArbeitseinsatzService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArbeitseinsatzController {

    private final ArbeitseinsatzService arbeitseinsatzService;

    @GetMapping("/arbeitseinsatz/list")
    public List<ArbeitseinsatzDto> listAvailable(@RequestParam(value = "name", defaultValue = "World") String name) {
        return arbeitseinsatzService.findAll();
    }

    @PostMapping("/arbeitseinsatz")
    @Transactional
    public Long addElement(@RequestBody ArbeitseinsatzDto element) {
        return arbeitseinsatzService.add(element);
    }

    @PutMapping("/arbeitseinsatz/{id}")
    @Transactional
    public Long updateElement(@PathVariable Long id, @RequestBody ArbeitseinsatzDto element) {
        return arbeitseinsatzService.update(id, element);
    }
}
