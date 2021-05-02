package de.rvneptun.repository;

import de.rvneptun.entity.Arbeitseinsatz;
import de.rvneptun.entity.Mitglied;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MitgliedRepository extends JpaRepository<Mitglied, Long> {
}

