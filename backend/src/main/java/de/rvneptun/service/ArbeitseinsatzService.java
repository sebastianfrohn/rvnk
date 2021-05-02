package de.rvneptun.service;

import de.rvneptun.dto.ArbeitseinsatzDto;
import de.rvneptun.entity.Arbeitseinsatz;
import de.rvneptun.mapper.ArbeitseinsatzMapper;
import de.rvneptun.mapper.MitgliedMapper;
import de.rvneptun.repository.ArbeitseinsatzRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArbeitseinsatzService {

    private final ArbeitseinsatzRepository arbeitseinsatzRepository;
    private final ArbeitseinsatzMapper arbeitseinsatzMapper;
    private final MitgliedMapper verantwortlicherMapper;

    public List<ArbeitseinsatzDto> findAll() {
        return arbeitseinsatzMapper.map(arbeitseinsatzRepository.findAll());
    }

    @Transactional
    public Long add(ArbeitseinsatzDto element) {
        return arbeitseinsatzRepository.save(arbeitseinsatzMapper.map(element)).getId();
    }

    @Transactional
    public Long update(Long id, ArbeitseinsatzDto arbeitseinsatzDto) {
        Arbeitseinsatz arbeitseinsatz = arbeitseinsatzRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("konnte keine Entity mit ID " + id + "finden"));

        Arbeitseinsatz newArbeitseinsatz = arbeitseinsatzMapper.map(arbeitseinsatzDto);

        arbeitseinsatz.setDescription(newArbeitseinsatz.getDescription());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        arbeitseinsatzRepository.deleteById(id);
    }

    public ArbeitseinsatzDto find(Long id) {
        return arbeitseinsatzMapper.map(arbeitseinsatzRepository
                .findById(id)
                .orElseThrow(() ->  new RuntimeException("Kein Element mit IDd + " + id +  " gefunden"))
        );
    }
}
