package de.rvneptun.repository;

import de.rvneptun.entity.ArbeitseinsatzEintrag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArbeitseinsatzEintragRepository extends JpaRepository<ArbeitseinsatzEintrag, Long> {

    @Query("select e from ArbeitseinsatzEintrag e where e.termin.organisator.id= ?1")
    List<ArbeitseinsatzEintrag> findAllByOrganisatorId(long id);

    @Query("select e from ArbeitseinsatzEintrag e left join e.mitglied m where m.id = ?1")
    List<ArbeitseinsatzEintrag> findAllByMitgliedId(long id);

}

