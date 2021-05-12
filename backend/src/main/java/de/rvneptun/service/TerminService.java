package de.rvneptun.service;

import de.rvneptun.dto.ArbeitseinsatzEintragDto;
import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.dto.TerminDto;
import de.rvneptun.entity.ArbeitseinsatzEintragStatus;
import de.rvneptun.entity.Mitglied;
import de.rvneptun.entity.Termin;
import de.rvneptun.exception.ForbiddenException;
import de.rvneptun.exception.TerminNotFoundException;
import de.rvneptun.mapper.TerminMapper;
import de.rvneptun.repository.TerminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static de.rvneptun.entity.Rolle.ADMIN;
import static de.rvneptun.misc.UserHelper.getAngemeldetesMitglied;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TerminService {

    private final TerminRepository terminRepository;
    private final TerminMapper terminMapper;

    public List<TerminDto> findAll() {
        return terminMapper.map(terminRepository.findAll());
    }

    public TerminDto findById(long id) {
        return terminMapper.map(terminRepository.findById(id).orElseThrow(() -> new TerminNotFoundException(id)));
    }

    @Transactional
    public void anmelden(long id) {
        Termin termin = terminRepository.findById(id).orElseThrow(() -> new TerminNotFoundException(id));
        Long userId = getAngemeldetesMitglied().getId();
        termin.getAnmeldungen().add(Mitglied.builder().id(userId).build());
    }

    @Transactional
    public void abmelden(long id) {
        Termin termin = terminRepository.findById(id).orElseThrow(() -> new TerminNotFoundException(id));
        Long userId = getAngemeldetesMitglied().getId();
        termin.getAnmeldungen().remove(Mitglied.builder().id(userId).build());
    }

    @Transactional
    public void loeschen(long id) {
        getTerminAndPruefeRechte(id);

        terminRepository.deleteById(id);
    }

    @Transactional
    public TerminDto save(TerminDto neuerTermin) {

        Termin termin = getTerminAndPruefeRechte(neuerTermin.getId());

        termin.setTitel(neuerTermin.getTitel());
        termin.setBeschreibung(neuerTermin.getBeschreibung());
        termin.setArbeitseinsatz(neuerTermin.isArbeitseinsatz());
        termin.setDatumVon(neuerTermin.getDatumVon());
        termin.setDatumBis(neuerTermin.getDatumBis());

        if (termin.getOrganisator() == null) {
            termin.setOrganisator(Mitglied.builder().id(getAngemeldetesMitglied().getId()).build());
        }

        return terminMapper.map(terminRepository.save(termin));
    }

    private Termin getTerminAndPruefeRechte(long id) {
        MitgliedDto angemeldetesMitglied = getAngemeldetesMitglied();

        Termin termin = terminRepository.findById(id).orElseGet(Termin::new);

        if (termin.getId() != 0 && isOrganisator(angemeldetesMitglied, termin) && angemeldetesMitglied.hasRolle(ADMIN)) {
            throw new ForbiddenException();
        }
        return termin;
    }

    private boolean isOrganisator(MitgliedDto angemeldetesMitglied, Termin termin) {
        return termin.getOrganisator().getId() != angemeldetesMitglied.getId();
    }

    public ArbeitseinsatzEintragDto getForTermin(long id) {
        TerminDto termin = terminMapper.map(terminRepository.findById(id).orElseThrow(() -> new TerminNotFoundException(id)));

        ArbeitseinsatzEintragDto dto = new ArbeitseinsatzEintragDto();
        dto.setTermin(termin);
        dto.setMitglied(getAngemeldetesMitglied());
        dto.setArbeitseinsatzEintragStatus(ArbeitseinsatzEintragStatus.OFFEN);

        return dto;
    }
}
