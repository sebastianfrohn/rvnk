package de.rvneptun.service;

import de.rvneptun.dto.TerminDto;
import de.rvneptun.entity.Termin;
import de.rvneptun.exception.TerminException;
import de.rvneptun.mapper.TerminMapper;
import de.rvneptun.mapper.MitgliedMapper;
import de.rvneptun.repository.TerminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TerminService {

    private final TerminRepository TerminRepository;
    private final TerminMapper TerminMapper;
    private final MitgliedMapper verantwortlicherMapper;

    public List<TerminDto> findAll() {
        return TerminMapper.map(TerminRepository.findAll());
    }

    @Transactional
    public Long add(TerminDto element) {
        return TerminRepository.save(TerminMapper.map(element)).getId();
    }

    @Transactional
    public Long update(Long id, TerminDto TerminDto) {
        Termin Termin = TerminRepository
                .findById(id)
                .orElseThrow(() -> new TerminException(id));

        Termin newTermin = TerminMapper.map(TerminDto);

        Termin.setDescription(newTermin.getDescription());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        TerminRepository.deleteById(id);
    }

    public TerminDto find(Long id) {
        return TerminMapper.map(TerminRepository
                .findById(id)
                .orElseThrow(() ->  new TerminException(id))
        );
    }
}
