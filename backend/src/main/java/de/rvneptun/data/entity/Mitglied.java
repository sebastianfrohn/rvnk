package de.rvneptun.data.entity;

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

    @Column(length = 128, nullable = false, unique = true)
    private String username;

    @Column(length = 64)
    private String vorname;

    @Column(length = 64)
    private String name;

    @Column(length = 64)
    private String password;

    @Column(length = 64)
    private String email;

    @ElementCollection(targetClass = Rolle.class)
    @Enumerated(EnumType.STRING)
    private List<Rolle> rollen;

    private String registertoken;
}
