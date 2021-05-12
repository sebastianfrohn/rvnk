package de.rvneptun.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Setter
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String titel;

    @Lob
    private String beschreibung;

    private LocalDateTime datumVon;

    private LocalDateTime datumBis;

    @ManyToMany
    private List<Mitglied> anmeldungen;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ArbeitseinsatzEintrag> arbeitsstunden;

    @ManyToOne
    private Mitglied organisator;

    private boolean arbeitseinsatz;

}



