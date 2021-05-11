package de.rvneptun.service;

import de.rvneptun.controller.dto.TerminDto;
import de.rvneptun.data.entity.Termin;
import de.rvneptun.misc.exception.TerminException;
import de.rvneptun.data.mapper.TerminMapper;
import de.rvneptun.data.repository.TerminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TerminService {

    private final TerminRepository terminRepository;
    private final TerminMapper terminMapper;

    public List<TerminDto> findAll() {
        return terminMapper.map(terminRepository.findAll());
    }

    @Transactional
    public Long add(TerminDto element) {
        return terminRepository.save(terminMapper.map(element)).getId();
    }

    @Transactional
    public Long update(Long id, TerminDto TerminDto) {
        Termin Termin = terminRepository
                .findById(id)
                .orElseThrow(() -> new TerminException(id));

        Termin newTermin = terminMapper.map(TerminDto);

        Termin.setBeschreibung(newTermin.getBeschreibung());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        terminRepository.deleteById(id);
    }

    public TerminDto find(Long id) {
        return terminMapper.map(terminRepository
                .findById(id)
                .orElseThrow(() ->  new TerminException(id))
        );
    }
}
