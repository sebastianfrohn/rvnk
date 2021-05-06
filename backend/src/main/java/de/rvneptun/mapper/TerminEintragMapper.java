package de.rvneptun.mapper;

import de.rvneptun.dto.TerminEintragDto;
import de.rvneptun.entity.TerminEintrag;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = MitgliedMapper.class)
public interface TerminEintragMapper {

    TerminEintrag map(TerminEintragDto TerminDto);

    TerminEintragDto mapReverse(TerminEintrag Termin);

    List<TerminEintrag> map(List<TerminEintragDto> list);

    List<TerminEintragDto> mapReverse(List<TerminEintrag> list);

}
