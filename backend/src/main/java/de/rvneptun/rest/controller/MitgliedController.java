package de.rvneptun.rest.controller;

import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.service.MitgliedService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("mitglied")
public class MitgliedController {

    private final MitgliedService mitgliedService;

    @GetMapping("/list")
    public List<MitgliedDto> listAvailable() {
        return mitgliedService.findAll();
    }

    @GetMapping("/{id}")
    public MitgliedDto find(@PathVariable Long id) {
        return mitgliedService.findById(id);
    }

    @PostMapping("/")
    @Transactional
    public Long add(@RequestBody MitgliedDto element) {
        return mitgliedService.add(element);
    }

    @PutMapping("/{id}")
    @Transactional
    public Long update(@PathVariable Long id, @RequestBody MitgliedDto element) {
        return mitgliedService.update(id, element);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void  delete(@PathVariable Long id) {
        mitgliedService.delete(id);
    }
}
