package de.rvneptun.data.repository;

import de.rvneptun.data.entity.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminRepository extends JpaRepository<Termin, Long> {
}

