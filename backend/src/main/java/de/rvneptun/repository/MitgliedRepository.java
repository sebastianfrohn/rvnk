package de.rvneptun.repository;

import de.rvneptun.entity.Arbeitseinsatz;
import de.rvneptun.entity.Mitglied;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MitgliedRepository extends JpaRepository<Mitglied, Long> {

    Optional<Mitglied> findByUsername(String username);
}

