package de.rvneptun.entity;

import de.rvneptun.misc.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Mitglied {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String vorname;

    private String name;

    private String email;

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

}
