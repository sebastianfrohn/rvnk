package de.rvneptun.dto;

import de.rvneptun.misc.Role;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MitgliedDto {

    private Long id;

    private String vorname;

    private String name;

    private String email;

    private String sessionId;

    private LocalDateTime sessionEnd;

    private List<Role> roles;

}
