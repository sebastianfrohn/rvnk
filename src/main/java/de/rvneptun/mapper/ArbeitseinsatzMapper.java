package de.rvneptun.mapper;

import de.rvneptun.dto.ArbeitseinsatzDto;
import de.rvneptun.entity.Arbeitseinsatz;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ArbeitseinsatzMapper {

    ArbeitseinsatzMapper INSTANCE = Mappers.getMapper(ArbeitseinsatzMapper.class);

    Arbeitseinsatz map(ArbeitseinsatzDto arbeitseinsatzDto);

    ArbeitseinsatzDto map(Arbeitseinsatz arbeitseinsatz);

    List<ArbeitseinsatzDto> map(List<Arbeitseinsatz> list);

}
