package de.rvneptun.data.mapper;

import de.rvneptun.controller.dto.ArbeitseinsatzEintragDto;
import de.rvneptun.controller.dto.MitgliedDto;
import de.rvneptun.controller.dto.TerminDto;
import de.rvneptun.controller.vo.TerminVo;
import de.rvneptun.data.entity.Rolle;
import de.rvneptun.misc.UserHelper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = MitgliedMapper.class)
public interface TerminVoMapper {

    @Mapping(source = "anmeldungen", target = "mitgliedAngemeldet", qualifiedByName = "mitgliedAngemeldet")
    @Mapping(source = "anmeldungen", target = "teilnehmerNamen", qualifiedByName = "teilnehmerNamen")
    @Mapping(source = "arbeitsstunden", target = "mitgliedArbeitsstunden", qualifiedByName = "mitgliedArbeitsstunden")
    @Mapping(source = "organisator", target = "darfBearbeiten", qualifiedByName = "darfBearbeiten")
    TerminVo map(TerminDto terminDto);

    @Mapping(source = "arbeitsstunden", target = "mitgliedArbeitsstunden", qualifiedByName = "mitgliedArbeitsstunden")
    @Mapping(source = "anmeldungen", target = "mitgliedAngemeldet", qualifiedByName = "mitgliedAngemeldet")
    List<TerminVo> map(List<TerminDto> list);

    @Named("mitgliedAngemeldet")
    static boolean isMitgliedAngemeldet(List<MitgliedDto> anmeldungen) {
        return Optional.ofNullable(UserHelper.getAngemeldetesMitglied())
                .map(MitgliedDto::getId)
                .map(id -> anmeldungen.stream()
                        .map(MitgliedDto::getId)
                        .anyMatch(x -> x.equals(id)))
                .orElse(false);
    }

    @Named("mitgliedArbeitsstunden")
    static boolean isMitgliedArbeitsstunden(List<ArbeitseinsatzEintragDto> arbeitsstunden) {
        return Optional.ofNullable(UserHelper.getAngemeldetesMitglied())
                .map(MitgliedDto::getId)
                .map(id -> arbeitsstunden.stream()
                        .map(ArbeitseinsatzEintragDto::getMitglied)
                        .map(MitgliedDto::getId)
                        .anyMatch(x -> x.equals(id)))
                .orElse(false);
    }

    @Named("teilnehmerNamen")
    static String mapTeilnehmerNamen(List<MitgliedDto> anmeldungen) {
        return anmeldungen.stream()
                .map(m -> m.getVorname() + "  " + m.getName())
                .collect(Collectors.joining(", "));
    }

    @Named("darfBearbeiten")
    static boolean darfBearbeiten(MitgliedDto organisator) {
        return Optional.ofNullable(UserHelper.getAngemeldetesMitglied())
                .map(user -> Optional.ofNullable(organisator).map(MitgliedDto::getId).map(id -> id.equals(user.getId())).orElse(false) || user.getRollen().contains(Rolle.ADMIN))
                .orElse(false);
    }

}
