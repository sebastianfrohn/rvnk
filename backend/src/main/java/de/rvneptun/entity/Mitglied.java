package de.rvneptun.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
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

    @ElementCollection(targetClass = Rolle.class)
    @Enumerated(EnumType.STRING)
    private List<Rolle> rollen;

    private String registertoken;

}
