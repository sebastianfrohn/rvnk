package de.rvneptun.repository;

import de.rvneptun.entity.DbScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DbSchemeRepository extends JpaRepository<DbScheme, Long> {

    @Query("select d.id from DbScheme d")
    List<Long> findAllIds();

}

