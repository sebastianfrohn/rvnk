package de.rvneptun.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime datumVon;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime datumBis;

    @Builder.Default
    private List<MitgliedDto> anmeldungen = new ArrayList<>();

    @Builder.Default
    private List<ArbeitseinsatzEintragDto> arbeitsstunden = new ArrayList<>();

    private MitgliedDto organisator;

    private boolean arbeitseinsatz;
}



