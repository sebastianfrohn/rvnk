package de.rvneptun.service;

import de.rvneptun.dto.ArbeitseinsatzEintragDto;
import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.entity.ArbeitseinsatzEintrag;
import de.rvneptun.enums.ArbeitseinsatzEintragStatus;
import de.rvneptun.exception.ForbiddenException;
import de.rvneptun.exception.MitgliedNotFoundException;
import de.rvneptun.exception.TerminNotFoundException;
import de.rvneptun.mapper.ArbeitseinsatzEintragMapper;
import de.rvneptun.misc.UserHelper;
import de.rvneptun.repository.ArbeitseinsatzEintragRepository;
import de.rvneptun.repository.MitgliedRepository;
import de.rvneptun.repository.TerminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static de.rvneptun.enums.Rolle.ADMIN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArbeitseinsatzEintragService {

    private final ArbeitseinsatzEintragRepository arbeitseinsatzEintragRepository;

    private final MitgliedRepository mitgliedRepository;

    private final TerminRepository terminRepository;

    private final ArbeitseinsatzEintragMapper arbeitseinsatzEintragMapper;

    public ArbeitseinsatzEintragDto findById(long id) {
        return arbeitseinsatzEintragMapper.mapReverse(arbeitseinsatzEintragRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<ArbeitseinsatzEintrag> findAllByMitgliedId(Long id) {
        return arbeitseinsatzEintragRepository.findAllByMitgliedId(id);
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

        eintrag.setMitglied(mitgliedRepository.findById(mitgliedId)
                .orElseThrow(() -> new MitgliedNotFoundException(mitgliedId)));
        eintrag.setTermin(terminRepository.findById(terminId)
                .orElseThrow(() -> new TerminNotFoundException(terminId)));

        eintrag.setArbeitseinsatzEintragStatus(ArbeitseinsatzEintragStatus.OFFEN);

        return arbeitseinsatzEintragMapper.mapReverse(arbeitseinsatzEintragRepository.save(eintrag));
    }

    private ArbeitseinsatzEintrag getEintragAndPruefeRechte(long id) {
        MitgliedDto angemeldetesMitglied = UserHelper.getAngemeldetesMitglied();

        ArbeitseinsatzEintrag einsatz = arbeitseinsatzEintragRepository.findById(id)
                .orElseGet(ArbeitseinsatzEintrag::new);

        if (einsatz.getId() != 0 && isOrganisator(angemeldetesMitglied, einsatz) && angemeldetesMitglied.hasRolle(ADMIN)) {
            throw new ForbiddenException();
        }
        return einsatz;
    }

    private boolean isOrganisator(MitgliedDto angemeldetesMitglied, ArbeitseinsatzEintrag einsatz) {
        return einsatz.getTermin().getOrganisator().getId() != angemeldetesMitglied.getId();
    }

}
