package de.rvneptun.entity;

import de.rvneptun.enums.ArbeitseinsatzEintragStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Getter
@Setter
public class ArbeitseinsatzEintrag {

    @Id
    @GeneratedValue(strategy = IDENTITY)
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



