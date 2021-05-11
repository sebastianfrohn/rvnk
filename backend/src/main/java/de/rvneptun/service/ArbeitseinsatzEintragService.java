package de.rvneptun.service;

import de.rvneptun.controller.dto.ArbeitseinsatzEintragDto;
import de.rvneptun.controller.dto.MitgliedDto;
import de.rvneptun.data.entity.ArbeitseinsatzEintrag;
import de.rvneptun.data.mapper.ArbeitseinsatzEintragMapper;
import de.rvneptun.data.repository.ArbeitseinsatzEintragRepository;
import de.rvneptun.data.repository.MitgliedRepository;
import de.rvneptun.data.repository.TerminRepository;
import de.rvneptun.misc.ArbeitseinsatzEintragStatus;
import de.rvneptun.misc.UserHelper;
import de.rvneptun.misc.exception.ArbeitseinsatzEintragException;
import de.rvneptun.misc.exception.ForbiddenException;
import de.rvneptun.misc.exception.MitgliedNotFoundException;
import de.rvneptun.misc.exception.TerminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static de.rvneptun.data.entity.Rolle.ADMIN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArbeitseinsatzEintragService {

    private final ArbeitseinsatzEintragRepository arbeitseinsatzEintragRepository;
    private final MitgliedRepository mitgliedRepository;
    private final TerminRepository terminRepository;
    private final ArbeitseinsatzEintragMapper arbeitseinsatzEintragMapper;


    public List<ArbeitseinsatzEintragDto> findAll() {
        return arbeitseinsatzEintragMapper.mapReverse(arbeitseinsatzEintragRepository.findAll());
    }

    public ArbeitseinsatzEintragDto findById(long id) {
        return arbeitseinsatzEintragMapper.mapReverse(arbeitseinsatzEintragRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public Long add(ArbeitseinsatzEintragDto element) {
        return arbeitseinsatzEintragRepository.save(arbeitseinsatzEintragMapper.map(element)).getId();
    }

    @Transactional
    public Long update(Long id, ArbeitseinsatzEintragDto arbeitseinsatzEintragDto) {
        ArbeitseinsatzEintrag arbeitseinsatz = arbeitseinsatzEintragRepository
                .findById(id)
                .orElseThrow(() -> new ArbeitseinsatzEintragException(id));

        ArbeitseinsatzEintrag newArbeitseinsatzEintrag = arbeitseinsatzEintragMapper.map(arbeitseinsatzEintragDto);

        arbeitseinsatz.setGeleisteteStunden(newArbeitseinsatzEintrag.getGeleisteteStunden());
        arbeitseinsatz.setDatum(newArbeitseinsatzEintrag.getDatum());

        return id;
    }

    public void delete(Long id) {
        arbeitseinsatzEintragRepository.deleteById(id);
    }

    public ArbeitseinsatzEintragDto find(Long id) {
        return arbeitseinsatzEintragMapper.mapReverse(
                arbeitseinsatzEintragRepository.findById(id).orElseThrow(() -> new ArbeitseinsatzEintragException(id))
        );
    }

    public List<ArbeitseinsatzEintrag> findAllByArbeiterId(Long id) {
        return arbeitseinsatzEintragRepository.findAllByArbeiterId(id);
    }

    public List<ArbeitseinsatzEintrag> findAllByOrganisatorId(Long id) {
        return arbeitseinsatzEintragRepository.findAllByOrganisatorId(id);
    }

    @Transactional
    public ArbeitseinsatzEintragDto save(ArbeitseinsatzEintragDto dto) {
        ArbeitseinsatzEintrag eintrag = getEintragAndPruefeRechte(dto.getId());

        eintrag.setGeleisteteStunden(dto.getGeleisteteStunden());

        Long mitgliedId = dto.getMitglied().getId();
        long terminId = dto.getTermin().getId();

        eintrag.setMitglied(mitgliedRepository.findById(mitgliedId).orElseThrow(() -> new MitgliedNotFoundException(mitgliedId)));
        eintrag.setTermin(terminRepository.findById(terminId).orElseThrow(() -> new TerminNotFoundException(terminId)));

        eintrag.setArbeitseinsatzEintragStatus(ArbeitseinsatzEintragStatus.OFFEN);

        return arbeitseinsatzEintragMapper.mapReverse(arbeitseinsatzEintragRepository.save(eintrag));
    }

    private ArbeitseinsatzEintrag getEintragAndPruefeRechte(long id) {
        MitgliedDto angemeldetesMitglied = UserHelper.getAngemeldetesMitglied();

        ArbeitseinsatzEintrag einsatz = arbeitseinsatzEintragRepository.findById(id).orElseGet(ArbeitseinsatzEintrag::new);

        if (einsatz.getId() != 0 && isOrganisator(angemeldetesMitglied, einsatz) && angemeldetesMitglied.hasRolle(ADMIN)) {
            throw new ForbiddenException();
        }
        return einsatz;
    }

    private boolean isOrganisator(MitgliedDto angemeldetesMitglied, ArbeitseinsatzEintrag einsatz) {
        return einsatz.getTermin().getOrganisator().getId() != angemeldetesMitglied.getId();
    }
}
