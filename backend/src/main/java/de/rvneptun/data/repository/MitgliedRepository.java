package de.rvneptun.data.repository;

import de.rvneptun.data.entity.Mitglied;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MitgliedRepository extends JpaRepository<Mitglied, Long> {

    Optional<Mitglied> findByUsername(String username);
}

