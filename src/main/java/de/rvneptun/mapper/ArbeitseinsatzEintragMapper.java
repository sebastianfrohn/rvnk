package de.rvneptun.mapper;

import de.rvneptun.dto.ArbeitseinsatzDto;
import de.rvneptun.dto.ArbeitseinsatzEintragDto;
import de.rvneptun.entity.Arbeitseinsatz;
import de.rvneptun.entity.ArbeitseinsatzEintrag;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArbeitseinsatzEintragMapper {

    ArbeitseinsatzEintrag map(ArbeitseinsatzEintragDto arbeitseinsatzDto);

    ArbeitseinsatzEintragDto mapReverse(ArbeitseinsatzEintrag arbeitseinsatz);

    List<ArbeitseinsatzEintrag> map(List<ArbeitseinsatzEintragDto> list);

    List<ArbeitseinsatzEintragDto> mapReverse(List<ArbeitseinsatzEintrag> list);

}
