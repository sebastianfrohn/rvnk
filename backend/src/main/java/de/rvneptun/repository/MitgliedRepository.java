package de.rvneptun.repository;

import de.rvneptun.entity.Mitglied;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MitgliedRepository extends JpaRepository<Mitglied, Long> {

    Optional<Mitglied> findByUsername(String username);

    @Query("select m from Mitglied m where m.registertoken = ?1")
    Optional<Mitglied> findOneByRegistertoken(String token);
}

