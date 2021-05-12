package de.rvneptun.mapper;

import de.rvneptun.dto.TerminDto;
import de.rvneptun.entity.Termin;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = MitgliedMapper.class)
public interface TerminMapper {

    Termin map(TerminDto arbeitseinsatzDto);

    TerminDto map(Termin arbeitseinsatz);

    List<TerminDto> map(List<Termin> list);

}
