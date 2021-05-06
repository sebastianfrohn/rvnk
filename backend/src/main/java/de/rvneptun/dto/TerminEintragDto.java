package de.rvneptun.dto;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TerminEintragDto {

    private long id;

    private Date datum;

    private MitgliedDto mitglied;

    private  TerminDto termin;
}



