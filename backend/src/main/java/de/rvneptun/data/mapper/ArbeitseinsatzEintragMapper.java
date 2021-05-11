package de.rvneptun.data.mapper;

import de.rvneptun.controller.dto.ArbeitseinsatzEintragDto;
import de.rvneptun.data.entity.ArbeitseinsatzEintrag;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = MitgliedMapper.class)
public interface ArbeitseinsatzEintragMapper {

    ArbeitseinsatzEintrag map(ArbeitseinsatzEintragDto arbeitseinsatzDto);

    ArbeitseinsatzEintragDto mapReverse(ArbeitseinsatzEintrag arbeitseinsatz);

    List<ArbeitseinsatzEintrag> map(List<ArbeitseinsatzEintragDto> list);

    List<ArbeitseinsatzEintragDto> mapReverse(List<ArbeitseinsatzEintrag> list);

}
