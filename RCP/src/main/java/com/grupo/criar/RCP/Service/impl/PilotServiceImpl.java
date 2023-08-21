package com.grupo.criar.RCP.Service.impl;

import com.grupo.criar.RCP.Models.Pilot;
import com.grupo.criar.RCP.Repository.PilotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PilotServiceImpl {

    public List<Pilot> getPilot();

    public Pilot getPilotByName(final String name) throws Exception;

    public List<Pilot> Add_File_To_Pilot();
}
