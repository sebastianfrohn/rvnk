package de.rvneptun.vo;

import de.rvneptun.dto.TerminDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TerminVo extends TerminDto {

    private String teilnehmerNamen;

    private boolean mitgliedAngemeldet;

    private boolean mitgliedArbeitsstunden;

    private boolean darfBearbeiten;

}



