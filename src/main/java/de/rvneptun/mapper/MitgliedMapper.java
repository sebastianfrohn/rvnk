package de.rvneptun.mapper;

import de.rvneptun.dto.MitgliedDto;
import de.rvneptun.entity.Mitglied;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MitgliedMapper {

    Mitglied map(MitgliedDto MitgliedDto);

    MitgliedDto map(Mitglied Verantwortlicher);

    List<MitgliedDto> map(List<Mitglied> list);

}
