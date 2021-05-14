package de.rvneptun.entity;

import de.rvneptun.enums.TerminTyp;
import de.rvneptun.enums.Wochentag;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Setter
public class Termin {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String titel;

    @Lob
    private String beschreibung;

    private LocalDateTime datumVon;

    private LocalDateTime datumBis;

    @Enumerated(EnumType.STRING)
    private Wochentag wochentag;

    @Enumerated(EnumType.STRING)
    private TerminTyp terminTyp;

    private int maxTeilnehmer;

    @ManyToMany
    private List<Mitglied> anmeldungen;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ArbeitseinsatzEintrag> arbeitsstunden;

    @ManyToOne
    private Mitglied organisator;

    private boolean arbeitseinsatz;

}



