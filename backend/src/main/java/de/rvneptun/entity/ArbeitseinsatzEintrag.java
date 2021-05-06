package de.rvneptun.entity;

import de.rvneptun.misc.ArbeitseinsatzEintragStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ArbeitseinsatzEintrag extends TerminEintrag {

    @OneToOne
    TerminEintrag terminEintrag;

    private Double geleisteteStunden;

    @Enumerated(EnumType.STRING)
    private ArbeitseinsatzEintragStatus arbeitseinsatzEintragStatus;

}



