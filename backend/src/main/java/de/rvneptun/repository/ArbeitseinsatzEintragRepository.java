package de.rvneptun.repository;

import de.rvneptun.entity.ArbeitseinsatzEintrag;
import de.rvneptun.entity.TerminEintrag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArbeitseinsatzEintragRepository extends JpaRepository<ArbeitseinsatzEintrag, Long> {
}

