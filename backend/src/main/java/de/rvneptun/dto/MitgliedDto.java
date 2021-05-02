package de.rvneptun.dto;

import de.rvneptun.misc.Role;
import lombok.*;

import javax.persistence.Entity;
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

    private List<Role> roles;

}
