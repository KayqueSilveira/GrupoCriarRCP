package com.grupo.criar.RCP.Service.impl;

import com.grupo.criar.RCP.Models.Pilot;
import com.grupo.criar.RCP.Models.Dto.Position;

import java.util.List;
public interface PositionServiceImpl {
    List<Position> PilotPosition(List<Pilot> pilots);

}
