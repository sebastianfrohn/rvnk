package de.rvneptun.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Arbeitseinsatz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String description;

    private Date datum;

    @ManyToOne(cascade = CascadeType.ALL)
    private Mitglied mitglied;
}



