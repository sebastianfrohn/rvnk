package de.rvneptun.service;

import de.rvneptun.dto.ArbeitseinsatzDto;
import de.rvneptun.entity.Arbeitseinsatz;
import de.rvneptun.mapper.ArbeitseinsatzMapper;
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
    private final ArbeitseinsatzMapper arbeitseinsatzMapper = ArbeitseinsatzMapper.INSTANCE;

    public List<ArbeitseinsatzDto> findAll() {
        return arbeitseinsatzMapper.map(arbeitseinsatzRepository.findAll());
    }

    @Transactional
    public Long add(ArbeitseinsatzDto element) {
        return arbeitseinsatzRepository.save(arbeitseinsatzMapper.map(element)).getId();
    }

    @Transactional
    public Long update(Long id, ArbeitseinsatzDto element) {
        Arbeitseinsatz arbeitseinsatz = arbeitseinsatzRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("konnte keine Entity mit ID " + id + "finden"));

        arbeitseinsatz.setContent(element.getContent());

        return id;
    }
}
