package de.rvneptun.repository;

import de.rvneptun.entity.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminRepository extends JpaRepository<Termin, Long> {
}

