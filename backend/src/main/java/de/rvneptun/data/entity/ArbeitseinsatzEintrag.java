package de.rvneptun.data.entity;

import de.rvneptun.misc.ArbeitseinsatzEintragStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ArbeitseinsatzEintrag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private LocalDateTime datum;

    private Double geleisteteStunden;

    @ManyToOne(cascade = CascadeType.ALL)
    private Mitglied mitglied;

    @Enumerated(EnumType.STRING)
    private ArbeitseinsatzEintragStatus arbeitseinsatzEintragStatus;

    @ManyToOne
    private Termin termin;
}



