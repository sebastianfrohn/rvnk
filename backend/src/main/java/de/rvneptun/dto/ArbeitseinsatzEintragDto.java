package de.rvneptun.dto;

import de.rvneptun.entity.Arbeitseinsatz;
import de.rvneptun.entity.Mitglied;
import de.rvneptun.misc.ArbeitseinsatzEintragStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArbeitseinsatzEintragDto {

    private long id;

    private Mitglied mitglied;

    private Arbeitseinsatz arbeitseinsatz;

    private Date datum;

    private Double geleisteteStunden;

    private ArbeitseinsatzEintragStatus arbeitseinsatzEintragStatus;

}



