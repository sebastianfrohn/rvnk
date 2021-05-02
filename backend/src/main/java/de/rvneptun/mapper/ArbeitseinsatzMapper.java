package de.rvneptun.mapper;

import de.rvneptun.dto.ArbeitseinsatzDto;
import de.rvneptun.entity.Arbeitseinsatz;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArbeitseinsatzMapper {

    Arbeitseinsatz map(ArbeitseinsatzDto arbeitseinsatzDto);

    ArbeitseinsatzDto map(Arbeitseinsatz arbeitseinsatz);

    List<ArbeitseinsatzDto> map(List<Arbeitseinsatz> list);

}
