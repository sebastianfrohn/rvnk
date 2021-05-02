package de.rvneptun.service;

import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.entity.Mitglied;
import de.rvneptun.mapper.MitgliedMapper;
import de.rvneptun.repository.MitgliedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MitgliedService {

    private final MitgliedRepository mitgliedRepository;
    private final MitgliedMapper mitgliedMapper;

    public List<MitgliedDto> findAll() {
        return mitgliedMapper.map(mitgliedRepository.findAll());
    }

    @Transactional
    public Long add(MitgliedDto element) {
        return mitgliedRepository.save(mitgliedMapper.map(element)).getId();
    }

    @Transactional
    public Long update(Long id, MitgliedDto miitgliedDto) {
        Mitglied mitglied = mitgliedRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("konnte keine Entity mit ID " + id + "finden"));

        Mitglied newMitglied = mitgliedMapper.map(miitgliedDto);

        mitglied.setName(newMitglied.getName());
        mitglied.setVorname(newMitglied.getVorname());
        mitglied.setEmail(newMitglied.getEmail());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        mitgliedRepository.deleteById(id);
    }

    public MitgliedDto find(Long id) {
        return mitgliedMapper.map(mitgliedRepository
                .findById(id)
                .orElseThrow(() ->  new RuntimeException("Kein Element mit ID + " + id +  " gefunden"))
        );
    }
}
