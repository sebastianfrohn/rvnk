package de.rvneptun.dto;

import de.rvneptun.entity.ArbeitseinsatzEintrag;
import de.rvneptun.entity.Mitglied;
import de.rvneptun.misc.ArbeitseinsatzEintragStatus;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArbeitseinsatzEintragDto  extends  TerminEintragDto {

    private TerminEintragDto terminEintrag;

    private Double geleisteteStunden;

    private ArbeitseinsatzEintragStatus arbeitseinsatzEintragStatus;

}



