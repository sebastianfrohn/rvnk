package de.rvneptun.service;

import de.rvneptun.controller.dto.TerminDto;
import de.rvneptun.data.entity.Mitglied;
import de.rvneptun.data.entity.Termin;
import de.rvneptun.misc.UserHelper;
import de.rvneptun.misc.exception.TerminException;
import de.rvneptun.data.mapper.TerminMapper;
import de.rvneptun.data.repository.TerminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public TerminDto findById(long id) {
        return terminMapper.map(terminRepository.findById(id).orElseThrow(() ->  new TerminException(id)));
    }
    @Transactional
    public void anmelden(long id) {
        Termin termin = terminRepository.findById(id).orElseThrow(() -> new TerminException(id));
        Long userId = UserHelper.getAngemeldetesMitglied().getId();
        termin.getAnmeldungen().add(Mitglied.builder().id(userId).build());
    }

    @Transactional
    public void abmelden(long id) {
        Termin termin = terminRepository.findById(id).orElseThrow(() -> new TerminException(id));
        Long userId = UserHelper.getAngemeldetesMitglied().getId();
        termin.getAnmeldungen().remove(Mitglied.builder().id(userId).build());
    }
}
