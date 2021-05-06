package de.rvneptun.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TerminDto {

    private long id;

    private String titel;

    private String description;

    private LocalDateTime datum;

    private MitgliedDto mitglied;
}



