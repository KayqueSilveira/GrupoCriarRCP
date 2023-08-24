package com.grupo.criar.RCP.Service.impl;

import com.grupo.criar.RCP.Models.Dto.PilotDto;
import com.grupo.criar.RCP.Models.Pilot;

import java.util.List;

public interface PilotServiceImpl {

    public List<Pilot> SavePilotRace();

    public List<Pilot> findAll();

    List<PilotDto> getAllPilot(List<Pilot> pilots);

    PilotDto getPilot(final String name);
}
