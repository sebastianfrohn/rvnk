package de.rvneptun.entity;

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
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String titel;

    @Lob
    private String description;

    private LocalDateTime datum;

    @ManyToOne(cascade = CascadeType.ALL)
    private Mitglied mitglied;
}



