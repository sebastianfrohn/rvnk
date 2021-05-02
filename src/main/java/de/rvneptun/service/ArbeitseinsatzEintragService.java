package de.rvneptun.service;

import de.rvneptun.dto.ArbeitseinsatzEintragDto;
import de.rvneptun.entity.ArbeitseinsatzEintrag;
import de.rvneptun.mapper.ArbeitseinsatzEintragMapper;
import de.rvneptun.mapper.MitgliedMapper;
import de.rvneptun.repository.ArbeitseinsatzEintragRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArbeitseinsatzEintragService {

    private final ArbeitseinsatzEintragRepository arbeitseinsatzEintragRepository;
    private final ArbeitseinsatzEintragMapper arbeitseinsatzEintragMapper;

    public List<ArbeitseinsatzEintragDto> findAll() {
        return arbeitseinsatzEintragMapper.mapReverse(arbeitseinsatzEintragRepository.findAll());
    }

    @Transactional
    public Long add(ArbeitseinsatzEintragDto element) {
        return arbeitseinsatzEintragRepository.save(arbeitseinsatzEintragMapper.map(element)).getId();
    }

    @Transactional
    public Long update(Long id, ArbeitseinsatzEintragDto arbeitseinsatzEintragDto) {
        ArbeitseinsatzEintrag arbeitseinsatz = arbeitseinsatzEintragRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("konnte keine Entity mit ID " + id + "finden"));

        ArbeitseinsatzEintrag newArbeitseinsatzEintrag = arbeitseinsatzEintragMapper.map(arbeitseinsatzEintragDto);

        arbeitseinsatz.setGeleisteteStunden(newArbeitseinsatzEintrag.getGeleisteteStunden());
        arbeitseinsatz.setDatum(newArbeitseinsatzEintrag.getDatum());

        return id;
    }

    public void delete(Long id) {
        arbeitseinsatzEintragRepository.deleteById(id);
    }

    public ArbeitseinsatzEintragDto find(Long id) {
        return arbeitseinsatzEintragMapper.mapReverse(arbeitseinsatzEintragRepository.findById(id).orElseThrow(() -> new RuntimeException()));
    }
}
