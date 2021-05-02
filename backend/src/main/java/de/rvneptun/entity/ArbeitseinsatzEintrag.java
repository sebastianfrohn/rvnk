package de.rvneptun.entity;

import de.rvneptun.misc.ArbeitseinsatzEintragStatus;
import de.rvneptun.misc.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ArbeitseinsatzEintrag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Mitglied mitglied;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Arbeitseinsatz arbeitseinsatz;

    private Date datum;

    private Double geleisteteStunden;

    @Enumerated(EnumType.STRING)
    private ArbeitseinsatzEintragStatus arbeitseinsatzEintragStatus;

}



