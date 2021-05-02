package de.rvneptun.dto;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArbeitseinsatzDto {

    private long id;

    private String description;

    private Date datum;

    private MitgliedDto mitglied;
}



