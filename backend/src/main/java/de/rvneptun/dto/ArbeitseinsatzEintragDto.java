package de.rvneptun.dto;

import de.rvneptun.misc.ArbeitseinsatzEintragStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private TerminDto termin;

}



