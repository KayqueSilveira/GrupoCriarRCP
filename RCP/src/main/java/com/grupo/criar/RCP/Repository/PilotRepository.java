package com.grupo.criar.RCP.Repository;

import com.grupo.criar.RCP.Models.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PilotRepository extends JpaRepository<Pilot, Long> {

    public Optional<Pilot> findByName (final String name);
}
