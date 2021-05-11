package de.rvneptun.data.mapper;

import de.rvneptun.controller.dto.ArbeitseinsatzEintragDto;
import de.rvneptun.controller.dto.MitgliedDto;
import de.rvneptun.controller.dto.TerminDto;
import de.rvneptun.controller.vo.TerminVo;
import de.rvneptun.misc.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = MitgliedMapper.class)
public interface TerminVoMapper {

    @Mapping(source = "anmeldungen", target = "mitgliedAngemeldet", qualifiedByName = "mitgliedAngemeldet")
    @Mapping(source = "arbeitsstunden", target = "mitgliedArbeitsstunden", qualifiedByName = "mitgliedArbeitsstunden")
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
                .map(id  -> arbeitsstunden.stream()
                        .map(ArbeitseinsatzEintragDto::getMitglied)
                        .map(MitgliedDto::getId)
                        .anyMatch(x -> x.equals(id)))
                .orElse(false);
    }

}
