package de.rvneptun.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String titel;

    @Lob
    private String beschreibung;

    private LocalDateTime datum;

    @Column(name="datumbis")
    private LocalDateTime datumBis;

    @ManyToMany
    private List<Mitglied> anmeldungen;

    @ManyToMany
    private List<ArbeitseinsatzEintrag> arbeitsstunden;

    @ManyToOne(cascade = CascadeType.ALL)
    private Mitglied organisator;
}



