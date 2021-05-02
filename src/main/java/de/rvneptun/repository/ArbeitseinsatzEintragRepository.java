package de.rvneptun.repository;

import de.rvneptun.entity.Arbeitseinsatz;
import de.rvneptun.entity.ArbeitseinsatzEintrag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArbeitseinsatzEintragRepository extends JpaRepository<ArbeitseinsatzEintrag, Long> {
}

