package de.rvneptun.entity;

import de.rvneptun.enums.Rolle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Mitglied {

    @Id
    @GeneratedValue(strategy = IDENTITY)
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
