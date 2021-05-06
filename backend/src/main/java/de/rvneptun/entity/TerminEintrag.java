package de.rvneptun.entity;

import de.rvneptun.misc.ArbeitseinsatzEintragStatus;
import de.rvneptun.misc.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class TerminEintrag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime datum;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Mitglied mitglied;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Termin termin;
}



