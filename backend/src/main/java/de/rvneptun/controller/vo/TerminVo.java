package de.rvneptun.controller.vo;

import de.rvneptun.controller.dto.ArbeitseinsatzEintragDto;
import de.rvneptun.controller.dto.MitgliedDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TerminVo {

    private long id;

    private String titel;

    private String beschreibung;

    private LocalDateTime datum;

    private LocalDateTime datumBis;

    private List<MitgliedDto> anmeldungen;

    private List<ArbeitseinsatzEintragDto> arbeitsstunden;

    private MitgliedDto organisator;

    private boolean mitgliedAngemeldet;

    private boolean mitgliedArbeitsstunden;
}



