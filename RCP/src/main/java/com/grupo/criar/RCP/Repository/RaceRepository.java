package com.grupo.criar.RCP.Repository;

import com.grupo.criar.RCP.Models.Race;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaceRepository extends JpaRepository<Race, Long> {

    List<Race> findByPilotName (final String name);
}
