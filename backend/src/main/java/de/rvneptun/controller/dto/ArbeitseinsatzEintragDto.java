package de.rvneptun.controller.dto;

import de.rvneptun.data.entity.Mitglied;
import de.rvneptun.misc.ArbeitseinsatzEintragStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArbeitseinsatzEintragDto {

    private long id;

    private LocalDateTime datum;

    private Double geleisteteStunden;

    private MitgliedDto mitglied;

    private ArbeitseinsatzEintragStatus arbeitseinsatzEintragStatus;

}



