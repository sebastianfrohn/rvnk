package de.rvneptun.controller.dto;

import de.rvneptun.data.entity.ArbeitseinsatzEintrag;
import de.rvneptun.data.entity.Mitglied;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TerminDto {

    private long id;

    private String titel;

    private String beschreibung;

    private LocalDateTime datum;

    private LocalDateTime datumBis;

    private List<MitgliedDto> anmeldungen;

    private List<ArbeitseinsatzEintragDto> arbeitsstunden;

    private MitgliedDto organisator;
}



