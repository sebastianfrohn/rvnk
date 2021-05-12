package de.rvneptun.mapper;

import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.entity.Mitglied;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MitgliedMapper {

    @Mapping(target = "registertoken", ignore = true)
    Mitglied map(MitgliedDto MitgliedDto);

    @Mapping(target = "authorities", ignore = true)
    MitgliedDto map(Mitglied Verantwortlicher);

    @Mapping(target = "authorities", ignore = true)
    List<MitgliedDto> map(List<Mitglied> list);

}
